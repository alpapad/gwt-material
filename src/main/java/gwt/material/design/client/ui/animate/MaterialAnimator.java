package gwt.material.design.client.ui.animate;

/*
 * #%L
 * GwtMaterialDesign
 * %%
 * Copyright (C) 2015 GwtMaterial
 * %%
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
 * #L%
 */

import gwt.material.design.client.resources.MaterialResources;
import gwt.material.design.client.ui.ListItem;
import gwt.material.design.client.ui.UnorderedList;

import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Widget;

public class MaterialAnimator {

	
	public static void animate(final Transition transition,final Widget w, int delayMillis){
		switch (transition) {
		case SHOW_STAGGERED_LIST:
			
			if(w instanceof UnorderedList){
				UnorderedList ul = (UnorderedList) w;
				
				for(Widget li : ul){
					if(li instanceof ListItem){
						li.getElement().getStyle().setOpacity(0);
					}
				}
			}
			break;
		default:
			break;
		}
		
		Timer t = new Timer() {
			
			@Override
			public void run() {
				applyAnimation(transition, w);
			}

			private void applyAnimation(Transition transition, Widget w) {
				String name = String.valueOf(this.hashCode());
				w.getElement().setId(name);
				switch (transition) {
				case SHOW_STAGGERED_LIST:
					showStaggeredList(name);
					break;
				case FADE_IN_IMAGE:
					fadeInImage(name);
					break;
				default:
					break;
				}
			}
		};
		t.schedule(delayMillis);
		w.removeStyleName(MaterialResources.INSTANCE.materialcss().pull());
		w.removeStyleName(MaterialResources.INSTANCE.materialcss().materialScale());
	}
	
	protected static native void fadeInImage(String name) /*-{
		$wnd.Materialize.fadeInImage('#' + name);
	}-*/;

	public static native void showStaggeredList(String name)/*-{
		$wnd.Materialize.showStaggeredList('#' + name);
	}-*/;
	
}
