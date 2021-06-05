package me.chame.discomine.minecraft.mixins;

import me.chame.discomine.minecraft.MCServer;
import me.chame.discomine.minecraft.MixinAdapter;
import net.minecraft.server.MinecraftServer;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.UUID;
import java.util.function.BooleanSupplier;

@Mixin(MinecraftServer.class)
public class MixinMinecraftServer {

/*     @Inject(at = @At("HEAD"), method = "sendSystemMessage")
    public void sendMessage(Text text, UUID uUID, CallbackInfo ci) {
        //FDLink1_16.handleText(text, uUID);
    } */

    @Inject(
            at = {@At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/server/MinecraftServer;setupServer()Z"
            )},
            method = {"runServer"}
    )
    private void beforeSetupServer(CallbackInfo info) {
        MixinAdapter.setServerStarted(true);
    }


    @Inject(
            at = {@At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/server/MinecraftServer;setFavicon(Lnet/minecraft/server/ServerMetadata;)V",
                    ordinal = 0
            )},
            method = {"runServer"}
    )
    private void afterSetupServer(CallbackInfo info) {
        MixinAdapter.setServerStarted(true);
    }

    @Inject(at = { @At("HEAD") }, method = { "shutdown" })
    private void beforeShutdownServer(CallbackInfo info) {
        MixinAdapter.setServerStopping(true);
    }

    @Inject(at = { @At("TAIL") }, method = { "shutdown" })
    private void afterShutdownServer(CallbackInfo info) {
        MixinAdapter.setserverStopped(true);
    }

    @Inject(at = {
            @At(value = "INVOKE", target = "Lnet/minecraft/server/MinecraftServer;tickWorlds(Ljava/util/function/BooleanSupplier;)V") }, method = {
                    "tick" })
    private void onStartTick(BooleanSupplier shouldKeepTicking, CallbackInfo ci) {
        MixinAdapter.setMinecraftServer(new MCServer((MinecraftServer) (Object) this));
        //FDLink.getMessageReceiver().serverTick(new MinecraftServer1_16((MinecraftServer) (Object) this));
    }
}