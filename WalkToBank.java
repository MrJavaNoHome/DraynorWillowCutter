package scripts;

import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.GameObject;
import org.powerbot.script.Tile;

public class WalkToBank extends Task<ClientContext> {
    private static final int willowLogId = 1519;
    private static final int[] bankBoothIds = { 6943,6946 };
    public static final Tile bankTile = new Tile(3092, 3245, 0);

    public WalkToBank(ClientContext ctx) {
        super(ctx);
    }


    @Override
    public boolean activate(){
        ctx.objects.select().id(bankBoothIds);
        GameObject bankBooth = ctx.objects.nearest().poll();
        return ctx.inventory.select().count() == 28 && !bankBooth.inViewport() && ctx.players.local().animation() == -1;
    }

    @Override
    public void execute(){
            System.out.println("Ide do banku");
            ctx.movement.step(bankTile);
            ctx.camera.turnTo(bankTile);
    }
}
