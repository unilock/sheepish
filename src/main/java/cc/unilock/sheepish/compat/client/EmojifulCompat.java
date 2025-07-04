/**
 * MIT License
 *
 * Copyright (c) 2022 Innovative Online Industries
 * Copyright (c) 2025 unilock
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package cc.unilock.sheepish.compat.client;

import com.esotericsoftware.yamlbeans.YamlReader;
import com.hrznstudio.emojiful.CommonClass;
import com.hrznstudio.emojiful.Constants;
import com.hrznstudio.emojiful.api.Emoji;
import com.hrznstudio.emojiful.api.EmojiCategory;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import static cc.unilock.sheepish.SheepishConfig.CONFIG;
import static com.hrznstudio.emojiful.ClientEmojiHandler.CATEGORIES;

public class EmojifulCompat {
	public static void init() {
		if (CONFIG.emojifulPixelatedTwemoji.value()) {
			loadPixelatedTwemojis();
		}
	}

	private static void loadPixelatedTwemojis() {
		try {
			YamlReader reader = new YamlReader(new StringReader(CommonClass.readStringFromURL("https://raw.githubusercontent.com/InnovativeOnlineIndustries/emojiful-assets/1.20-plus/Categories.yml")));
			ArrayList<String> categories = (ArrayList<String>) reader.read();
			for (String category : categories) {
				if ("Blobs.yml".equals(category) || "Discord.yml".equals(category) || "Pepe.yml".equals(category)) continue;
				CATEGORIES.add(new EmojiCategory(category.replace(".yml", ""), false));
				List<Emoji> emojis = CommonClass.readCategory(category);
				emojis.forEach(emoji -> emoji.location = CommonClass.cleanURL(emoji.location));
				emojis.forEach(emoji -> emoji.name = "custom_" + emoji.name);
				Constants.EMOJI_LIST.addAll(emojis);
				Constants.EMOJI_MAP.put(category.replace(".yml", ""), emojis);
			}
		} catch (Exception e) {
			Constants.error = true;
			Constants.LOG.error("An exception was caught whilst loading custom emojis", e);
		}
	}
}
