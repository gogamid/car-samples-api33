/*
 * Copyright (C) 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package androidx.car.app.sample.showcase.common.textandicons;

import static androidx.car.app.model.Action.BACK;
import static androidx.car.app.model.CarColor.YELLOW;

import android.text.SpannableString;

import androidx.annotation.NonNull;
import androidx.car.app.CarContext;
import androidx.car.app.Screen;
import androidx.car.app.model.CarIcon;
import androidx.car.app.model.ItemList;
import androidx.car.app.model.ListTemplate;
import androidx.car.app.model.Row;
import androidx.car.app.model.Template;
import androidx.car.app.sample.showcase.common.R;
import androidx.car.app.sample.showcase.common.common.Utils;
import androidx.core.graphics.drawable.IconCompat;
import androidx.lifecycle.DefaultLifecycleObserver;

/** Creates a screen that shows different types of rows in a list */
public final class RowDemoScreen extends Screen implements DefaultLifecycleObserver {
    private static final String FULL_STAR = "\u2605";
    private static final String HALF_STAR = "\u00BD";

    public RowDemoScreen(@NonNull CarContext carContext) {
        super(carContext);
        getLifecycle().addObserver(this);
    }

    private static CharSequence getRatingsString(Double ratings) {
        String s;
        double r;
        for (s = "", r = ratings; r > 0; --r) {
            s += r < 1 ? HALF_STAR : FULL_STAR;
        }
        SpannableString ss = new SpannableString(s + " ratings: " + ratings);
        if (!s.isEmpty()) {
            Utils.colorize(ss, YELLOW, 0, s.length());
        }
        return ss;
    }

    @NonNull
    @Override
    public Template onGetTemplate() {
        ItemList.Builder listBuilder = new ItemList.Builder();

        listBuilder.addItem(new Row.Builder()
                .setTitle(getCarContext().getString(R.string.just_row_title))
                .build());
        listBuilder.addItem(
                new Row.Builder()
                        .setTitle(getCarContext().getString(R.string.title_with_app_icon_row_title))
                        .setImage(CarIcon.APP_ICON)
                        .build());

        listBuilder.addItem(
                new Row.Builder()
                        .setTitle(getCarContext().getString(
                                R.string.title_with_res_id_image_row_title))
                        .setImage(
                                new CarIcon.Builder(
                                        IconCompat.createWithResource(
                                                getCarContext(),
                                                R.drawable.ic_fastfood_white_48dp))
                                        .build(),
                                Row.IMAGE_TYPE_ICON)
                        .build());

        listBuilder.addItem(
                new Row.Builder()
                        .setTitle(
                                getCarContext().getString(R.string.title_with_svg_image_row_title))
                        .setImage(
                                new CarIcon.Builder(
                                        IconCompat.createWithResource(
                                                getCarContext(),
                                                R.drawable
                                                        .ic_emoji_food_beverage_white_48dp))
                                        .build(),
                                Row.IMAGE_TYPE_ICON)
                        .build());

        listBuilder.addItem(
                new Row.Builder()
                        .setTitle(getCarContext().getString(
                                R.string.title_with_secondary_lines_row_title))
                        .addText(getCarContext().getString(
                                R.string.title_with_secondary_lines_row_text_1))
                        .addText(getCarContext().getString(
                                R.string.title_with_secondary_lines_row_text_2))
                        .build());

        listBuilder.addItem(
                new Row.Builder()
                        .setTitle(getCarContext().getString(R.string.colored_secondary_row_title))
                        .addText(getRatingsString(3.5))
                        .build());

        return new ListTemplate.Builder()
                .setSingleList(listBuilder.build())
                .setTitle(getCarContext().getString(R.string.rows_demo_title))
                .setHeaderAction(BACK)
                .build();
    }
}
