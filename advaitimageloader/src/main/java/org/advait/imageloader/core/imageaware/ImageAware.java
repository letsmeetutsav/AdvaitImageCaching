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
package org.advait.imageloader.core.imageaware;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.View;
import org.advait.imageloader.core.assist.ViewScaleType;
import org.advait.imageloader.core.DisplayImageOptions;
import org.advait.imageloader.core.ImageLoader;
import org.advait.imageloader.core.display.BitmapDisplayer;
import org.advait.imageloader.core.listener.ImageLoadingListener;

/**
 * Represents image aware view which provides all needed properties and behavior for image processing and displaying
 * through {@link ImageLoader ImageLoader}.
 * It can wrap any Android {@link android.view.View View} which can be accessed by {@link #getWrappedView()}. Wrapped
 * view is returned in {@link ImageLoadingListener ImageLoadingListener}'s
 * callbacks.
 *
 * @author Utsav (letsmeetutsav@gmail.com)
 * @see ViewAware
 * @see ImageViewAware
 * @see NonViewAware
 * @since 1.0.0
 */
public interface ImageAware {
	/**
	 * Returns width of image aware view. This value is used to define scale size for original image.
	 * Can return 0 if width is undefined.<br />
	 * Is called on UI thread if ImageLoader was called on UI thread. Otherwise - on background thread.
	 */
	int getWidth();

	/**
	 * Returns height of image aware view. This value is used to define scale size for original image.
	 * Can return 0 if height is undefined.<br />
	 * Is called on UI thread if ImageLoader was called on UI thread. Otherwise - on background thread.
	 */
	int getHeight();

	/**
	 * Returns {@linkplain ViewScaleType scale type} which is used for
	 * scaling image for this image aware view. Must <b>NOT</b> return <b>null</b>.
	 */
	ViewScaleType getScaleType();

	/**
	 * Returns wrapped Android {@link android.view.View View}. Can return <b>null</b> if no view is wrapped or view was
	 * collected by GC.<br />
	 * Is called on UI thread if ImageLoader was called on UI thread. Otherwise - on background thread.
	 */
	View getWrappedView();

	/**
	 * Returns a flag whether image aware view is collected by GC or whatsoever. If so then ImageLoader stop processing
	 * of task for this image aware view and fires
	 * {@link ImageLoadingListener#onLoadingCancelled(String,
	 * android.view.View) ImageLoadingListener#onLoadingCancelled(String, View)} callback.<br />
	 * Mey be called on UI thread if ImageLoader was called on UI thread. Otherwise - on background thread.
	 *
	 * @return <b>true</b> - if view is collected by GC and ImageLoader should stop processing this image aware view;
	 * <b>false</b> - otherwise
	 */
	boolean isCollected();

	/**
	 * Returns ID of image aware view. Point of ID is similar to Object's hashCode. This ID should be unique for every
	 * image view instance and should be the same for same instances. This ID identifies processing task in ImageLoader
	 * so ImageLoader won't process two image aware views with the same ID in one time. When ImageLoader get new task
	 * it cancels old task with this ID (if any) and starts new task.
	 * <p/>
	 * It's reasonable to return hash code of wrapped view (if any) to prevent displaying non-actual images in view
	 * because of view re-using.
	 */
	int getId();

	/**
	 * Sets image drawable into this image aware view.<br />
	 * Displays drawable in this image aware view
	 * {@linkplain DisplayImageOptions.Builder#showImageForEmptyUri(
	 *android.graphics.drawable.Drawable) for empty Uri},
	 * {@linkplain DisplayImageOptions.Builder#showImageOnLoading(
	 *android.graphics.drawable.Drawable) on loading} or
	 * {@linkplain DisplayImageOptions.Builder#showImageOnFail(
	 *android.graphics.drawable.Drawable) on loading fail}. These drawables can be specified in
	 * {@linkplain DisplayImageOptions display options}.<br />
	 * Also can be called in {@link BitmapDisplayer BitmapDisplayer}.< br />
	 * Is called on UI thread if ImageLoader was called on UI thread. Otherwise - on background thread.
	 *
	 * @return <b>true</b> if drawable was set successfully; <b>false</b> - otherwise
	 */
	boolean setImageDrawable(Drawable drawable);

	/**
	 * Sets image bitmap into this image aware view.<br />
	 * Displays loaded and decoded image {@link android.graphics.Bitmap} in this image view aware.
	 * Actually it's used only in
	 * {@link BitmapDisplayer BitmapDisplayer}.< br />
	 * Is called on UI thread if ImageLoader was called on UI thread. Otherwise - on background thread.
	 *
	 * @return <b>true</b> if bitmap was set successfully; <b>false</b> - otherwise
	 */
	boolean setImageBitmap(Bitmap bitmap);
}
