package core;

import listener.Playlist;
import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.MemberCachePolicy;
import net.dv8tion.jda.api.utils.cache.CacheFlag;

import javax.security.auth.login.LoginException;

public class Main {

    public static JDABuilder builder;

    public static void main(String[] args) throws LoginException {

        Storage.init();
        if(!Storage.propExist("search"))
            Storage.addKey("search", "1");

        builder = new JDABuilder(AccountType.BOT).setDisabledIntents(GatewayIntent.GUILD_PRESENCES).setMemberCachePolicy(MemberCachePolicy.NONE).disableCache(CacheFlag.ACTIVITY, CacheFlag.CLIENT_STATUS);
        builder.setStatus(OnlineStatus.ONLINE);
        builder.setAutoReconnect(true);

        builder.setToken(Storage.getValue("token"));
        builder.addEventListeners(new Playlist());

        builder.build();
    }

}
