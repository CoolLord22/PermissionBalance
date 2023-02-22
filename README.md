# PermissionBalance
A simple addon for Essentials Economy to support permission based max balances.

## Config
```yaml
#######################
# Config ##############
#######################
bal-limit:
  default: "100000"

  # permissionbalance.multiple.vip
  vip: "500000"

  # permissionbalance.multiple.staff
  staff: "1000000"
```
You can create a `new rank` and then simply give players the permission `permissionbalance.multiple.<new rank>`. The 
plugin will handle the rest! It automatically checks which maximum balance permission the player has, and will apply it
to all transactions. 

Unfortunately, there is a limitation that the plugin essentially acts as a maximum cap. This means, the player can still
be paid by other players (and they will lose their money) but the player will only receive enough money to hit the cap.
Similar concepts apply to other transactions: the player can sell items, and will lose them, regardless of if the max
balance has been reached or not.