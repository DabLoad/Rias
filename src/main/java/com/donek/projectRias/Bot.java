package com.donek.projectRias;

import com.donek.projectRias.commands.Music.*;
import com.donek.projectRias.commands.*;
import com.donek.projectRias.listeners.EventListener;
import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.sharding.ShardManager;
import net.dv8tion.jda.api.utils.MemberCachePolicy;
import net.dv8tion.jda.api.utils.cache.CacheFlag;

import javax.security.auth.login.LoginException;

public class Bot {
    private final ShardManager shardManager;
    private final Dotenv config;
    public Bot() throws LoginException {
        config = Dotenv.configure().ignoreIfMissing().load();
        String token = config.get("TOKEN");
        DefaultShardManagerBuilder builder = DefaultShardManagerBuilder.createDefault(token,
                GatewayIntent.GUILD_MESSAGES,
                GatewayIntent.GUILD_VOICE_STATES,
                GatewayIntent.GUILD_EMOJIS_AND_STICKERS,
                GatewayIntent.SCHEDULED_EVENTS,
                GatewayIntent.MESSAGE_CONTENT,
                GatewayIntent.GUILD_MEMBERS,
                GatewayIntent.GUILD_PRESENCES,
                GatewayIntent.GUILD_MESSAGE_REACTIONS);
        builder.setStatus(OnlineStatus.ONLINE);
        builder.setActivity(Activity.watching("DxD"));
        builder.setMemberCachePolicy(MemberCachePolicy.VOICE);
        builder.enableCache(CacheFlag.VOICE_STATE);
        shardManager = builder.build();

        shardManager.addEventListener(new EventListener(),
                new CommandManager(),
                new Info(),
                new Reactions(),
                new Ping(),
                new PlayCommand(),
                new StopCommand(),
                new SkipCommand(),
                new NowPlayingCommand(),
                new QueueCommand(),
                new RepeatCommand(),
                new LeaveCommand(),
                new VerificationCreateCommand());
    }
    public Dotenv getConfig(){
        return config;
    }
    public ShardManager getShardManager() {
        return shardManager;
    }

    public static void main(String[] args) {
        try {
            Bot bot = new Bot();
        } catch (LoginException e) {
            System.out.println("ERROR: Provided bot token is invalid!");
        }

    }
}
