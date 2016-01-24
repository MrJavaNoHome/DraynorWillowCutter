package scripts;


import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.GameObject;
import java.lang.Thread;
import org.powerbot.script.Script;

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
        //System.out.println("Chop");
        GameObject tree = ctx.objects.nearest().poll();

        if(!tree.valid()){
            System.out.println("Drzewo notValid");
            GameObject tree2 = ctx.objects.nearest().poll();
            tree2.interact("Chop");
        }
        else if(!tree.valid() && ctx.players.local().animation() == 867) {
            System.out.println("Brak drzewa w widoku i gracz nie jest w ruchu");
            ctx.camera.turnTo(tree);
            ctx.movement.step(tree);
        }
        else if(tree.inViewport() && !ctx.players.local().inMotion() && ctx.players.local().animation() != 867)
        {
            System.out.println("Drzewo w widoku i gracz nie rusza sie i nie scina drzewa");
            tree.interact("Chop");
        }

    }
}
