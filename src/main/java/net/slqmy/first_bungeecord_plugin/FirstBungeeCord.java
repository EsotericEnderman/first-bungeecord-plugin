package net.slqmy.first_bungeecord_plugin;

import com.google.common.collect.ImmutableList;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.api.plugin.PluginManager;
import net.md_5.bungee.api.scheduler.ScheduledTask;
import net.slqmy.first_bungeecord_plugin.commands.FruitCommand;
import net.slqmy.first_bungeecord_plugin.commands.MessageCommand;
import net.slqmy.first_bungeecord_plugin.commands.PingCommand;
import net.slqmy.first_bungeecord_plugin.commands.ReplyCommand;
import net.slqmy.first_bungeecord_plugin.event_listeners.ConnectionListener;
import net.slqmy.first_bungeecord_plugin.event_listeners.ProxyPingEventListener;
import net.slqmy.first_bungeecord_plugin.utility.DebugUtility;
import net.slqmy.first_bungeecord_plugin.utility.PluginUtility;
import org.enginehub.squirrelid.Profile;
import org.enginehub.squirrelid.resolver.HttpRepositoryService;
import org.enginehub.squirrelid.resolver.ParallelProfileService;
import org.enginehub.squirrelid.resolver.ProfileService;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public final class FirstBungeeCord extends Plugin {
	private final Map<String, String> recentMessages = new HashMap<>();

	@Override
	public void onEnable() {
		final ProxyServer proxyServer = ProxyServer.getInstance();
		final PluginManager pluginManager = proxyServer.getPluginManager();

		pluginManager.registerCommand(this, new PingCommand());
		pluginManager.registerCommand(this, new FruitCommand());
		pluginManager.registerCommand(this, new MessageCommand(this));
		pluginManager.registerCommand(this, new ReplyCommand(this));

		pluginManager.registerListener(this, new ConnectionListener(this));
		pluginManager.registerListener(this, new ProxyPingEventListener(this));

		final ScheduledTask task = proxyServer.getScheduler().schedule(this, () -> {
			PluginUtility.log("New scheduler cycle has started!");
		}, 1, 300, TimeUnit.MINUTES);

		PluginUtility.log("Task ID: " + task.getId());

		/*

		 3 Ways to cancel tasks:

		 1. proxyServer.getScheduler().cancel(task.getId());
		 2. proxyServer.getScheduler().cancel(task);
		 3. proxyServer.getScheduler().cancel(this);

		*/

		try {
			final ProfileService resolver = HttpRepositoryService.forMinecraft();

			// You can do this other way around with UUIDs as well.
			final Profile profile = resolver.findByName("Notch");

			if (profile == null) {
				PluginUtility.log("Profile of player 'Notch' not found!");
			} else {
				PluginUtility.log("Notch's UUID: " + profile.getUniqueId());
			}

			final ImmutableList<Profile> profiles = resolver.findAllByName(
							Arrays.asList("Notch", "Slqmy", "Slvmy", "Dream", "TechnoBlade")
			);

			for (final Profile currentProfile : profiles) {
				PluginUtility.log("UUID of player " + currentProfile.getName() + ": " + currentProfile.getUniqueId());
			}

			// Doing it on separate threads:
			final ParallelProfileService service = new ParallelProfileService(resolver, 3);
			service.findAllByName(
							Arrays.asList("Notch", "Slqmy", "Slvmy", "Dream", "TechnoBlade"),
							(final Profile currentProfile) -> {
								PluginUtility.log("UUID of player " + currentProfile.getName() + ": " + currentProfile.getUniqueId());

								return true;
							}
			);
		} catch (final IOException | InterruptedException exception) {
			DebugUtility.logError(exception, "There was an error looking up players!");
		}
	}

	public Map<String, String> getRecentMessages() {
		return recentMessages;
	};
}