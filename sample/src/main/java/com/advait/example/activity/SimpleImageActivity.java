/*******************************************************************************
 * Copyright 2024
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package com.advait.example.activity;

import android.os.Bundle;

import androidx.fragment.app.FragmentActivity;

import com.advait.example.R;
import com.advait.example.fragment.ImageGridFragment;

/**
 * @author Utsav (letsmeetutsav@gmail.com)
 */
public class SimpleImageActivity extends FragmentActivity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ImageGridFragment fr = new ImageGridFragment();
		String tag = ImageGridFragment.class.getSimpleName();
		int titleRes = R.string.ac_name_image_grid;
		setTitle(titleRes);
		getSupportFragmentManager().beginTransaction().replace(android.R.id.content, fr, tag).commit();
	}
}