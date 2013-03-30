/**
 * Copyright 2012 John Ericksen
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.example.androiddraw;

import android.app.ListActivity;
import android.widget.ArrayAdapter;
import org.androidtransfuse.annotations.*;
import org.androidtransfuse.intentFactory.IntentFactory;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author John Ericksen
 */
@Activity(label = "@string/app_name", type = ListActivity.class)
@IntentFilter({
        @Intent(type = IntentType.ACTION, name = android.content.Intent.ACTION_MAIN),
        @Intent(type = IntentType.CATEGORY, name = android.content.Intent.CATEGORY_LAUNCHER)
})
public class Selection {
    private static final long THE_ANSWER = 42;
    private static final String TEST_VALUE = "test value";

    private List<ActivityListItem> values;
    private ListActivity listActivity;
    private IntentFactory intentFactory;

    @Inject
    public Selection(ListActivity listActivity, IntentFactory intentFactory) {

        this.intentFactory = intentFactory;
        this.listActivity = listActivity;

        values = new ArrayList<ActivityListItem>(Arrays.asList(new ActivityListItem[]{
                createLI(SimpleDrawActivity.class, "Simple Draw"),
                createLI(QuadDrawActivity.class, "Quad Draw"),
                createLI(CubicDrawActivity.class, "Cubic Splines Draw"),
                createLI(SquareSignatureDrawActivity.class, "Square Signature"),
        }));

        ArrayAdapter<ActivityListItem> adapter = new ArrayAdapter<ActivityListItem>(listActivity, android.R.layout.simple_list_item_1, values);
        listActivity.setListAdapter(adapter);
    }

    @OnListItemClick
    public void onListItemClick(int position) {
        listActivity.startActivity(values.get(position).getIntent());
    }

    private ActivityListItem createLI(Class<?> clazz, String name) {
        android.content.Intent intent = new android.content.Intent(listActivity, clazz);
        return new ActivityListItem(intent, name);
    }
}
