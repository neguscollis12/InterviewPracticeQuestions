# Question 4 — Bank Account Transaction System

Implement deposit, withdraw, and transfer operations across bank accounts with daily withdrawal limits.

## Requirements

- Each account has a balance and a daily withdrawal limit.
- Deposits always succeed for a valid, existing account.
- Withdrawals fail if the amount exceeds the balance, or if the amount would exceed the account's remaining daily withdrawal limit.
- Transfers move funds from one account to another; they follow the same withdrawal rules on the source account, and the destination account is credited only if the withdrawal succeeds.
- The daily withdrawal limit remaining resets only via an explicit `ResetDailyLimit` operation (simulating a new day), not automatically.

## Messages to Return

| Status | Message |
|---|---|
| Deposit success | `<accountId> deposited <amount> successfully!` |
| Deposit fail: not found | `<accountId> does not exist!` |
| Withdraw success | `<accountId> withdrew <amount> successfully!` |
| Withdraw fail: insufficient funds | `<accountId> has insufficient funds!` |
| Withdraw fail: limit exceeded | `<accountId> has exceeded the daily withdrawal limit!` |
| Transfer success | `<amount> transferred from <fromId> to <toId> successfully!` |
| Transfer fail: insufficient funds | `<fromId> has insufficient funds!` |
| Transfer fail: limit exceeded | `<fromId> has exceeded the daily withdrawal limit!` |

## Function Description

### Account Class — implements IAccount

- constructor: `(id, balance, dailyLimit)`
- properties: `Id`, `Balance`, `DailyLimit`, `RemainingLimit` (initialized to `DailyLimit`)

### BankSystem Class — implements IBankSystem

- constructor takes a list of `IAccount`
- `Deposit(IAccount account, double amount) → string`
- `Withdraw(IAccount account, double amount) → string`
- `Transfer(IAccount from, IAccount to, double amount) → string`
- `ResetDailyLimit(IAccount account) → void`

## Input Format for Custom Testing

```text
n                                  // number of accounts
n lines of: id, balance, dailyLimit
k                                  // number of operations
k lines of: FunctionName:accountIndex(:secondAccountIndex):amount
```

### Sample Input

```text
2
A1, 500, 300
A2, 100, 300
3
Withdraw:0:200
Withdraw:0:200
Transfer:0:1:50
```

### Sample Output

```text
A1 withdrew 200 successfully!
A1 has exceeded the daily withdrawal limit!
50 transferred from A1 to A2 successfully!
```

## Notes

- `ResetDailyLimit` should restore the account's `RemainingLimit` to its original daily limit.
- Transfer should behave like a withdrawal from the source account and a credit to the destination account.
- If the source account cannot complete the withdrawal, the transfer fails without changing the destination account balance.
- Deposit should only be applied to an existing account; otherwise, it fails with the not-found message.
