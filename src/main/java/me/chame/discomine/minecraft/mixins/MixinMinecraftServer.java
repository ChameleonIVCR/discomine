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

/**
 * DiscoMine main Mixins, the mappings are declared manually at the refmap.json
 * file. Every inject should be commented to explain what it does. These inject
 * themselves in a not destructive fashion.
 */

@Mixin(MinecraftServer.class)
public class MixinMinecraftServer {

    //TODO
    /*
     * @Inject(at = @At("HEAD"), method = "sendSystemMessage") public void
     * sendMessage(Text text, UUID uUID, CallbackInfo ci) {
     * //FDLink1_16.handleText(text, uUID); }
     */

    //Runs when the server is starting.
    @Inject(at = { @At(value = "INVOKE", target = "Lnet/minecraft/server/MinecraftServer;setupServer()Z") }, method = {
            "runServer" })
    private void beforeSetupServer(CallbackInfo info) {
        MixinAdapter.setServerStarted(true);
    }

    //Runs after the server has started.
    @Inject(at = {
            @At(value = "INVOKE", target = "Lnet/minecraft/server/MinecraftServer;setFavicon(Lnet/minecraft/server/ServerMetadata;)V", ordinal = 0) }, method = {
                    "runServer" })
    private void afterSetupServer(CallbackInfo info) {
        MixinAdapter.setServerStarted(true);
    }

    //Runs before the server shuts down.
    @Inject(at = { @At("HEAD") }, method = { "shutdown" })
    private void beforeShutdownServer(CallbackInfo info) {
        MixinAdapter.setServerStopping(true);
    }

    //Runs when the server has shut down. This may not be called if JDA doesn't stop in the previous call.
    @Inject(at = { @At("TAIL") }, method = { "shutdown" })
    private void afterShutdownServer(CallbackInfo info) {
        MixinAdapter.setserverStopped(true);
    }

    //Runs after the first server tick event is fired. It casts itself to the Mixin adapter.
    @Inject(at = {
            @At(value = "INVOKE", target = "Lnet/minecraft/server/MinecraftServer;tickWorlds(Ljava/util/function/BooleanSupplier;)V") }, method = {
                    "tick" })
    private void onStartTick(BooleanSupplier shouldKeepTicking, CallbackInfo ci) {
        MixinAdapter.setMinecraftServer(new MCServer((MinecraftServer) (Object) this));
        // FDLink.getMessageReceiver().serverTick(new
        // MinecraftServer1_16((MinecraftServer) (Object) this));
    }
}