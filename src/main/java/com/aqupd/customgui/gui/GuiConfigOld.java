package com.aqupd.customgui.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiSlider;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import java.io.IOException;
import java.util.Collections;

import static com.aqupd.customgui.CustomHandGUI.*;
import static com.aqupd.customgui.util.Configuration.*;

public class GuiConfigOld extends GuiScreen {

	GuiButton buttons;
	GuiSlider sliders;

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		GL11.glColor4f(1, 1, 1, 1);
		mc.renderEngine.bindTexture(new ResourceLocation(MOD_ID, "textures/gui/configgui.png"));
		if (lefthandedit) {
			drawTexturedModalRect(
					width / 2 + 10,
					height - 44 - 84 - 16 - 128,
					0,
					0,
					128,
					128
			);
		} else {
			drawTexturedModalRect(
					width / 2 + 10,
					height - 44 - 84 - 16 - 128,
					128,
					0,
					128,
					128
			);
		}
		drawCenteredString(
			Minecraft.getMinecraft().fontRenderer,
			I18n.format("config.aqupd.position"),
			width / 2 + 75 + 2,
			height - 44 - 84 - 16,
			16777215
		);
		drawCenteredString(
			Minecraft.getMinecraft().fontRenderer,
			I18n.format("config.aqupd.rotation"),
			width / 2 - 75 - 2,
			height - 44 - 84 - 16,
			16777215
		);
		drawCenteredString(Minecraft.getMinecraft().fontRenderer, "Hand GUI Editor", width / 2, 10, 16777215);

		super.drawScreen(mouseX, mouseY, partialTicks);
		for (GuiButton guiButton : buttonList) {
			if (guiButton != null && guiButton.isMouseOver()) {
				switch (guiButton.id) {
					case 0:
						drawHoveringText(
							Collections.singletonList(I18n.format("config.aqupd.swaphands.desc")),
							mouseX,
							mouseY,
							fontRenderer
						);
						break;
					case 1:
						drawHoveringText(
							Collections.singletonList(I18n.format("config.aqupd.resetposition.desc")),
							mouseX,
							mouseY,
							fontRenderer
						);
						break;
					case 8:
						drawHoveringText(
							Collections.singletonList(I18n.format("config.aqupd.swapchat.desc")),
							mouseX,
							mouseY,
							fontRenderer
						);
						break;
					case 9:
						drawHoveringText(
							Collections.singletonList(I18n.format("config.aqupd.autorefresh.desc")),
							mouseX,
							mouseY,
							fontRenderer
						);
						break;
					case 10:
						drawHoveringText(
								Collections.singletonList(I18n.format("config.aqupd.handedit.desc")),
								mouseX,
								mouseY,
								fontRenderer
						);
						break;
				}
			}
		}
	}

	@Override
	public void initGui() {
		buttonList.clear();
		buttonList.add(
				buttons =
						new GuiButton(
								0,
								width / 2 - 128 / 2,
								height - 44,
								128,
								20,
								this.mc.gameSettings.getKeyBinding(GameSettings.Options.MAIN_HAND)
						)
		);
		buttonList.add(
				buttons =
						new GuiButton(
								8,
								width / 2 - 128 / 2 - 128 - 2,
								height - 44,
								128,
								20,
								I18n.format("config.aqupd.swapchat") + ": " + (swapChat ? I18n.format("gui.yes") : I18n.format("gui.no"))
						)
		);
		buttonList.add(
				buttons =
						new GuiButton(
								9,
								width / 2 + 128 / 2 + 2,
								height - 44,
								128,
								20,
								I18n.format("config.aqupd.autorefresh") + ": " + (update ? I18n.format("gui.yes") : I18n.format("gui.no"))
						)
		);
		buttonList.add(
				buttons =
						new GuiButton(
								10,
								width / 2 - 128 - 1,
								height - 44 - 22,
								128,
								20,
								I18n.format("config.aqupd.handedit") + ": " + (lefthandedit ? I18n.format("gui.left.hand") : I18n.format("gui.right.hand"))
						)
		);
		buttonList.add(
				buttons =
						new GuiButton(
								1,
								width / 2 + 1,
								height - 44 - 22,
								128,
								20,
								I18n.format("config.aqupd.resetposition")
						)
		);

		if(lefthandedit) {
			buttonList.add(
					sliders =
							new GuiSlider(
									new GuiSliderResponder(),
									2,
									width / 2 + 1,
									height - 44 - 44,
									"z",
									-10F,
									1F,
									(float) z2Gui,
									(id, name, value) -> name + ": " + String.format("%.2f", value)
							)
			);
			buttonList.add(
					sliders =
							new GuiSlider(
									new GuiSliderResponder(),
									3,
									width / 2 + 1,
									height - 44 - 65,
									"y",
									-5F,
									5F,
									(float) y2Gui,
									(id, name, value) -> name + ": " + String.format("%.2f", value)
							)
			);
			buttonList.add(
					sliders =
							new GuiSlider(
									new GuiSliderResponder(),
									4,
									width / 2 + 1,
									height - 44 - 86,
									"x",
									-5F,
									10F,
									(float) x2Gui,
									(id, name, value) -> name + ": " + String.format("%.2f", value)
							)
			);

			buttonList.add(
					sliders =
							new GuiSlider(
									new GuiSliderResponder(),
									5,
									width / 2 - 151,
									height - 44 - 44,
									"z",
									-179F,
									180F,
									z2Rot,
									(id, name, value) -> name + ": " + (int) value
							)
			);
			buttonList.add(
					sliders =
							new GuiSlider(
									new GuiSliderResponder(),
									6,
									width / 2 - 151,
									height - 44 - 65,
									"y",
									-179F,
									180F,
									y2Rot,
									(id, name, value) -> name + ": " + (int) value
							)
			);
			buttonList.add(
					sliders =
							new GuiSlider(
									new GuiSliderResponder(),
									7,
									width / 2 - 151,
									height - 44 - 86,
									"x",
									-179F,
									180F,
									x2Rot,
									(id, name, value) -> name + ": " + (int) value
							)
			);
		} else {
			buttonList.add(
					sliders =
							new GuiSlider(
									new GuiSliderResponder(),
									2,
									width / 2 + 1,
									height - 44 - 44,
									"z",
									-10F,
									1F,
									(float) z1Gui,
									(id, name, value) -> name + ": " + String.format("%.2f", value)
							)
			);
			buttonList.add(
					sliders =
							new GuiSlider(
									new GuiSliderResponder(),
									3,
									width / 2 + 1,
									height - 44 - 65,
									"y",
									-5F,
									5F,
									(float) y1Gui,
									(id, name, value) -> name + ": " + String.format("%.2f", value)
							)
			);
			buttonList.add(
					sliders =
							new GuiSlider(
									new GuiSliderResponder(),
									4,
									width / 2 + 1,
									height - 44 - 86,
									"x",
									-5F,
									10F,
									(float) x1Gui,
									(id, name, value) -> name + ": " + String.format("%.2f", value)
							)
			);

			buttonList.add(
					sliders =
							new GuiSlider(
									new GuiSliderResponder(),
									5,
									width / 2 - 151,
									height - 44 - 44,
									"z",
									-179F,
									180F,
									z1Rot,
									(id, name, value) -> name + ": " + (int) value
							)
			);
			buttonList.add(
					sliders =
							new GuiSlider(
									new GuiSliderResponder(),
									6,
									width / 2 - 151,
									height - 44 - 65,
									"y",
									-179F,
									180F,
									y1Rot,
									(id, name, value) -> name + ": " + (int) value
							)
			);
			buttonList.add(
					sliders =
							new GuiSlider(
									new GuiSliderResponder(),
									7,
									width / 2 - 151,
									height - 44 - 86,
									"x",
									-179F,
									180F,
									x1Rot,
									(id, name, value) -> name + ": " + (int) value
							)
			);
		}
		super.initGui();
	}

	@Override
	protected void actionPerformed(GuiButton button) throws IOException {
		switch (button.id) {
			case 0:
				this.mc.gameSettings.setOptionValue(GameSettings.Options.MAIN_HAND, 1);
				button.displayString = this.mc.gameSettings.getKeyBinding(GameSettings.Options.MAIN_HAND);
				this.mc.gameSettings.sendSettingsToServer();
				break;
			case 1:
				resetHand();
				mc.displayGuiScreen(new GuiConfigOld());
				break;
			case 8:
				changeChat();
				mc.displayGuiScreen(new GuiConfigOld());
				break;
			case 9:
				changeUpdate();
				mc.displayGuiScreen(new GuiConfigOld());
				break;
			case 10:
				lefthandedit = !lefthandedit;
				mc.displayGuiScreen(new GuiConfigOld());
				saveOptions();
				break;
		}
		super.actionPerformed(button);
	}

	@Override
	protected void keyTyped(char typedChar, int keyCode) throws IOException {
		switch (keyCode) {
			case Keyboard.KEY_E:
			case Keyboard.KEY_G:
				mc.displayGuiScreen(null);
				break;
		}
		super.keyTyped(typedChar, keyCode);
	}

	@Override
	protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
		super.mouseClicked(mouseX, mouseY, mouseButton);
		if(lefthandedit) {
			if ((width / 2 + 2 <= mouseX) && (width / 2 + 2 + 150 >= mouseX)) {
				if ((height - 44 - 86 <= mouseY) && (height - 44 - 86 + 20 >= mouseY)) {
					if ((xguitimer + 200) > System.currentTimeMillis()) {
						setHandPos("2x", (double) 0);
						mc.displayGuiScreen(new GuiConfigOld());
					}
					xguitimer = System.currentTimeMillis();
				}
				if ((height - 44 - 65 <= mouseY) && (height - 44 - 65 + 20 >= mouseY)) {
					if ((yguitimer + 200) > System.currentTimeMillis()) {
						setHandPos("2y", (double) 0);
						mc.displayGuiScreen(new GuiConfigOld());
					}
					yguitimer = System.currentTimeMillis();
				}
				if ((height - 44 - 44 <= mouseY) && (height - 44 - 44 + 20 >= mouseY)) {
					if ((zguitimer + 200) > System.currentTimeMillis()) {
						setHandPos("2z", (double) 0);
						mc.displayGuiScreen(new GuiConfigOld());
					}
					zguitimer = System.currentTimeMillis();
				}
			}
			if ((width / 2 - 2 >= mouseX) && (width / 2 - 2 - 150 <= mouseX)) {
				if ((height - 44 - 86 <= mouseY) && (height - 44 - 86 + 20 >= mouseY)) {
					if ((xrottimer + 200) > System.currentTimeMillis()) {
						setHandRot("2x", (float) 0);
						mc.displayGuiScreen(new GuiConfigOld());
					}
					xrottimer = System.currentTimeMillis();
				}
				if ((height - 44 - 65 <= mouseY) && (height - 44 - 65 + 20 >= mouseY)) {
					if ((yrottimer + 200) > System.currentTimeMillis()) {
						setHandRot("2y", (float) 0);
						mc.displayGuiScreen(new GuiConfigOld());
					}
					yrottimer = System.currentTimeMillis();
				}
				if ((height - 44 - 44 <= mouseY) && (height - 44 - 44 + 20 >= mouseY)) {
					if ((zrottimer + 200) > System.currentTimeMillis()) {
						setHandRot("2z", (float) 0);
						mc.displayGuiScreen(new GuiConfigOld());
					}
					zrottimer = System.currentTimeMillis();
				}
			}
		} else {
			if ((width / 2 + 2 <= mouseX) && (width / 2 + 2 + 150 >= mouseX)) {
				if ((height - 44 - 86 <= mouseY) && (height - 44 - 86 + 20 >= mouseY)) {
					if ((xguitimer + 200) > System.currentTimeMillis()) {
						setHandPos("1x", (double) 0);
						mc.displayGuiScreen(new GuiConfigOld());
					}
					xguitimer = System.currentTimeMillis();
				}
				if ((height - 44 - 65 <= mouseY) && (height - 44 - 65 + 20 >= mouseY)) {
					if ((yguitimer + 200) > System.currentTimeMillis()) {
						setHandPos("1y", (double) 0);
						mc.displayGuiScreen(new GuiConfigOld());
					}
					yguitimer = System.currentTimeMillis();
				}
				if ((height - 44 - 44 <= mouseY) && (height - 44 - 44 + 20 >= mouseY)) {
					if ((zguitimer + 200) > System.currentTimeMillis()) {
						setHandPos("1z", (double) 0);
						mc.displayGuiScreen(new GuiConfigOld());
					}
					zguitimer = System.currentTimeMillis();
				}
			}
			if ((width / 2 - 2 >= mouseX) && (width / 2 - 2 - 150 <= mouseX)) {
				if ((height - 44 - 86 <= mouseY) && (height - 44 - 86 + 20 >= mouseY)) {
					if ((xrottimer + 200) > System.currentTimeMillis()) {
						setHandRot("1x", (float) 0);
						mc.displayGuiScreen(new GuiConfigOld());
					}
					xrottimer = System.currentTimeMillis();
				}
				if ((height - 44 - 65 <= mouseY) && (height - 44 - 65 + 20 >= mouseY)) {
					if ((yrottimer + 200) > System.currentTimeMillis()) {
						setHandRot("1y", (float) 0);
						mc.displayGuiScreen(new GuiConfigOld());
					}
					yrottimer = System.currentTimeMillis();
				}
				if ((height - 44 - 44 <= mouseY) && (height - 44 - 44 + 20 >= mouseY)) {
					if ((zrottimer + 200) > System.currentTimeMillis()) {
						setHandRot("1z", (float) 0);
						mc.displayGuiScreen(new GuiConfigOld());
					}
					zrottimer = System.currentTimeMillis();
				}
			}
		}
	}
}
