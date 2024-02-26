package name.synchro.guidance;

import com.mojang.blaze3d.systems.RenderSystem;
import name.synchro.SynchroClient;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.CyclingButtonWidget;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;

public class GuidanceScreen extends HandledScreen<GuidanceScreenHandler> {
    public CyclingButtonWidget<Boolean> newHudButton;
    public TextFieldWidget debugData0;
    public TextFieldWidget debugData1;
    public TextFieldWidget debugData2;
    public TextFieldWidget debugData3;
    public ButtonWidget saveButton;
    public GuidanceScreen(GuidanceScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    @Override
    protected void drawForeground(MatrixStack matrices, int mouseX, int mouseY) {
        this.textRenderer.draw(matrices, this.title, (float)this.titleX, (float)this.titleY, 0xffffff);
    }

    @Override
    protected void drawBackground(MatrixStack matrices, float delta, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexProgram);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
    }
    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        renderBackground(matrices);
        super.render(matrices, mouseX, mouseY, delta);
        debugData0.render(matrices, mouseX, mouseY, delta);
        debugData1.render(matrices, mouseX, mouseY, delta);
        debugData2.render(matrices, mouseX, mouseY, delta);
        debugData3.render(matrices, mouseX, mouseY, delta);
        drawMouseoverTooltip(matrices, mouseX, mouseY);
    }
    @Override
    protected void init() {
        super.init();
        titleX = (backgroundWidth - textRenderer.getWidth(title)) / 2;
        newHudButton = CyclingButtonWidget.onOffBuilder(SynchroClient.applyNewHud).build(width / 2 - 50, height / 2, 100, 20, Text.of("Apply new HUD"),
                (button, value) -> SynchroClient.applyNewHud = value);
        debugData0 = new TextFieldWidget(this.textRenderer, width / 2 - 105, height / 2 + 30 , 100, 10, Text.of("Debug Data0"));
        debugData1 = new TextFieldWidget(this.textRenderer, width / 2 + 5, height / 2 + 30 , 100, 10, Text.of("Debug Data1"));
        debugData2 = new TextFieldWidget(this.textRenderer, width / 2 - 105, height / 2 + 45 , 100, 10, Text.of("Debug Data2"));
        debugData3 = new TextFieldWidget(this.textRenderer, width / 2 + 5, height / 2 + 45 , 100, 10, Text.of("Debug Data3"));
        debugData0.setText(Float.toString(SynchroClient.debugNum0));
        debugData1.setText(Float.toString(SynchroClient.debugNum1));
        debugData2.setText(Float.toString(SynchroClient.debugNum2));
        debugData3.setText(Float.toString(SynchroClient.debugNum3));
        debugData0.setMaxLength(16);
        debugData1.setMaxLength(16);
        debugData2.setMaxLength(16);
        debugData3.setMaxLength(16);
        saveButton = ButtonWidget.builder(Text.of("Save"), button -> saveDebugData()).build();
        saveButton.setX(width / 2 - 50);
        saveButton.setY(height / 2 + 60);
        saveButton.setWidth(100);
        addDrawableChild(newHudButton);
        addDrawableChild(saveButton);
        addSelectableChild(debugData0);
        addSelectableChild(debugData1);
        addSelectableChild(debugData2);
        addSelectableChild(debugData3);
    }

    @Override
    public void resize(MinecraftClient client, int width, int height) {
        String debugDataString0 = debugData0.getText();
        String debugDataString1 = debugData1.getText();
        String debugDataString2 = debugData2.getText();
        String debugDataString3 = debugData3.getText();
        init(client,width,height);
        debugData0.setText(debugDataString0);
        debugData1.setText(debugDataString1);
        debugData2.setText(debugDataString2);
        debugData3.setText(debugDataString3);
    }

    private void saveDebugData(){
        SynchroClient.debugNum0 = parseFloat(debugData0.getText());
        SynchroClient.debugNum1 = parseFloat(debugData1.getText());
        SynchroClient.debugNum2 = parseFloat(debugData2.getText());
        SynchroClient.debugNum3 = parseFloat(debugData3.getText());
    }

    private static float parseFloat(String string) {
        try {
            return Float.parseFloat(string);
        } catch (NumberFormatException numberFormatException) {
            return 0f;
        }
    }
}
