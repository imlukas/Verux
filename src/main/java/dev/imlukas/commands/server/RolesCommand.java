package dev.imlukas.commands.server;

import dev.imlukas.util.command.SlashCommand;
import dev.imlukas.util.command.SlashCommandContext;
import dev.imlukas.util.command.annotations.SlashCommandHandler;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;

import java.util.List;

public class RolesCommand implements SlashCommand {

    @SlashCommandHandler()
    public void run(SlashCommandContext ctx) {
        Guild guild = ctx.getGuild();
        List<Role> roleList = guild.getRoles();

        EmbedBuilder embedBuilder = new EmbedBuilder();
        for (Role role : roleList) {

            String roleName = role.getName();
            String roleID = role.getId();
            List<Member> roleMembers = guild.getMembersWithRoles(role);

            embedBuilder.addField(roleName, "| ID: " + roleID + " | Members: " + roleMembers.size(), true);
        }

        ctx.getEvent().replyEmbeds(embedBuilder.build()).queue();
    }

    @Override
    public String getName() {
        return "roles";
    }

    @Override
    public String getDescription() {
        return "Get a list of the available roles";
    }
}
