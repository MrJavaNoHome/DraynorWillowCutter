package scripts.DraynorWillowCutter.DraynorWillowCutter;

import org.powerbot.script.PollingScript;
import org.powerbot.script.Script;
import org.powerbot.script.rt4.ClientContext;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

/**
 * Tutorial used:
 * http://www.powerbot.org/community/topic/1182786-the-rsbot-cookbook-writing-your-first-script-using-tasks/
 */
@Script.Manifest(
        name = "GramNaPsp's Draynor Willow Chopper", properties = "author=GramNaPsp; topic=-1; client=4;",
        description = "Cut willow trees in Draynor Village."
)
public class DraynorWillowChopper extends PollingScript<ClientContext> {
    private List<Task> taskList = new ArrayList<Task>();

    @Override
    public void start() {
        Chop chopper = new Chop(ctx);
        BankItems banker = new BankItems(ctx);
        WalkToBank walker = new WalkToBank(ctx);
        //WalkToTrees treeWalker = new WalkToTrees(ctx);
        //taskList.addAll(Arrays.asList(chopper,banker,walker,treeWalker));
        taskList.addAll(Arrays.asList(chopper,walker,banker));
    }

    @Override
    public void poll() {
        for(Task task : taskList)
        {
            if(task.activate()){
                task.execute();
            }
        }
    }

    @Override
    public void stop() {
        System.out.println("Script Stopped! Thanks for using it :)");
    }
}