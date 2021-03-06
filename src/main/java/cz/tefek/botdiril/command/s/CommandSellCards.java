package cz.tefek.botdiril.command.s;

import java.util.Arrays;
import java.util.List;

import cz.tefek.botdiril.command.Command;
import cz.tefek.botdiril.command.CommandCategory;
import cz.tefek.botdiril.core.ServerPreferences;
import cz.tefek.botdiril.userdata.UserStorage;
import cz.tefek.botdiril.userdata.items.Item;
import cz.tefek.botdiril.userdata.items.ItemPair;
import cz.tefek.botdiril.userdata.items.card.ItemCard;
import net.dv8tion.jda.core.entities.Message;

public class CommandSellCards implements Command
{
    @Override
    public Class<?>[] getArgumentTypes()
    {
        return new Class<?>[] { String.class };
    }

    @Override
    public List<String> getAliases()
    {
        return Arrays.asList("sellcards");
    }

    @Override
    public void interpret(Message message, Object... params)
    {
        if (params.length == 0)
        {
            var prefix = ServerPreferences.getServerByID(message.getGuild().getIdLong()).getPrefix();
            var msg = String.format("This sells all your card. This cannot be undone. **Keep in mind cards have a very low sell value.**\n To proceed type `%ssellcards confirm`. Just ignore this message if you changed your mind.", prefix);
            message.getTextChannel().sendMessage(msg).submit();

            return;
        }

        if (((String) params[0]).equalsIgnoreCase("confirm"))
        {
            var ui = UserStorage.getByID(message.getAuthor().getIdLong());

            var total = 0L;

            for (ItemPair is : ui.getInventory(-1))
            {
                if (is.getItem() instanceof ItemCard)
                {
                    var item = is.getItem();
                    var amount = is.getAmount();

                    var coins = amount * item.getSellValue();

                    total += coins;

                    ui.sellItems(item, coins, amount);
                }
            }

            message.getTextChannel().sendMessage(String.format("You sold all your cards for %d %ss total.", total, Item.COINDIRIL)).submit();
        }
    }

    @Override
    public String usage()
    {
        return "sellcards [confirm]";
    }

    @Override
    public String description()
    {
        return "Sell all your cards. NO UNDO";
    }

    @Override
    public boolean canRunWithoutArguments()
    {
        return true;
    }

    @Override
    public CommandCategory getCategory()
    {
        return CommandCategory.ITEMS;
    }
}
