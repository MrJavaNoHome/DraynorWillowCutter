package scripts.DraynorWillowCutter.DraynorWillowCutter;

import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.GameObject;
import org.powerbot.script.rt4.Bank;
import org.powerbot.script.Tile;

public class BankItems extends Task<ClientContext> {
    private static final int[] bankBoothIds = { 6943,6946 };
    public static final Tile treeTile = new Tile(3090, 3234, 0);

    private Bank bank;

    public BankItems(ClientContext ctx) {
        super(ctx);
        this.bank = new Bank(ctx);
    }

    @Override
    public boolean activate(){
        ctx.objects.select().id(bankBoothIds);
        GameObject bankBooth = ctx.objects.nearest().poll();
        return ctx.inventory.select().count() == 28 && bankBooth.inViewport();
    }

    @Override
    public void execute(){

        GameObject bankBooth = ctx.objects.select().name("Bank booth").nearest().poll();
        if(bankBooth != null)
        {
            System.out.println("Found bank booth");
            bankBooth.interact("Bank");

            while(!bank.opened());

            bank.depositInventory();

            //Wait while it deposit
            while(ctx.inventory.select().count() == 28);
            bank.close();
            //While unitl bank is closed - not needed
            //while(bank.opened());
            ctx.movement.step(treeTile);
            ctx.camera.turnTo(treeTile);
        }else
        {
            System.out.println("Cant find bank booth");
        }
    }
}
