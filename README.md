#IPRanger

This BungeeCord plugin is a companion to [MCBouncerBungeeCord](https://github.com/MCBouncer/MCBouncerBungeeCord), designed to automatically ban users connecting from specified IP ranges.

## Example Config

IPRanger will accept either a single IP per line in its list, or wildcards with the `*` character. The sample below affects users connecting from [MCLeaks](https://www.reddit.com/r/admincraft/comments/4jm6wd/dealing_with_compromised_mcleaks_accounts/) using the IP blocks known at the time of this writing.

```
api_key: "REPLACE"
message: "Compromised account"

ips:
- "151.80.11.*"
- "164.132.80.*"
- "176.31.75.*"
- "178.33.27.*"
- "91.121.231.*"
```

## Commands

`/ipranger-reload` - Reload the config file if you have the `ipranger.reload` permission node

## Permissions

* `ipranger.notify` - Receive broadcasts when a player is banned
