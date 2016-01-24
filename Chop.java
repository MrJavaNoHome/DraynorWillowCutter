package scripts.DraynorWillowCutter.DraynorWillowCutter;


import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.GameObject;

public class Chop extends Task<ClientContext> {
    private static final int[] treeIds = {7482,7422,7480,7424};

    public Chop(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate(){
        return ctx.inventory.select().count() < 28 &&
                !ctx.objects.select().id(treeIds).isEmpty() && ctx.players.local().animation() == -1;

    }

    @Override
    public void execute(){
        GameObject tree = ctx.objects.nearest().poll();

        if(!tree.valid()) {
            System.out.println("No tree");
            ctx.camera.turnTo(tree);
            ctx.movement.step(tree);
        }
        else if(tree.inViewport() && !ctx.players.local().inMotion() && ctx.players.local().animation() != 867)
        {
            System.out.println("Found tree and player doing 'nothing'");
            tree.interact("Chop");
        }

    }
}
