/*
 * Author information
 * 	Name: Hengxiu Gao
 *  UTD ID: 2021229554
 *  Email: hxg140930@utdallas.com
 *  
 * Computer Graphics Home work: Game of Tetris
 * Due by 11:55pm, Sunday, 2 November 2014
 * 
 * Game Description:
 * 
 * At beginning, a setting UI will be displayed to change the default setting,
 * in the setting UI, the game mode has been grouped as "Easy", "Normal", "Hard".
 * When selecting each mode, the according factor (M,N,S) will be changed differently.
 * The game factor can also be individually modified by the slider. Also, the stage 
 * size and square size can be modified by the slider in the setting UI. 
 * 
 * The new shapes and new colors can also be added in the setting UI by clicking the 
 * "customize shapes" and "Add new color" button. When clicking the "Customize shapes"
 * button, a shape editor frame will be displayed and player can make their own shapes.
 * When clicking the "Customize colors" button, a color editor frame will be displayed 
 * and player can make their own color.
 * 
 */
package com.hengxiugao.CGcourse;


public class Main {
	
	
	public static void main(String[] args) {
		PlayData.setDefaultShapes();
		PlayData.setDefaultColors();
		/*
		Setting s = new Setting();
		s.setSWING();
		*/
		AppView appview = new AppView();
		appview.setSwing();
	}

}