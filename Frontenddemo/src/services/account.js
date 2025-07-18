// src/services/account.js (Mock version)

export async function getAccountDetails(accountNumber) {
  await new Promise(resolve => setTimeout(resolve, 500)); // simulate delay
  return {
    accountHolderName: "James",
    accountNumber: "1234567890",
    accountType: "Savings",
    balance: 15000.75,
    transactions: [
      {
        id: 1,
        timestamp: new Date().toISOString(),
        description: "Grocery Shopping",
        type: "DEBIT",
        amount: 1200
      },
      {
        id: 2,
        timestamp: new Date().toISOString(),
        description: "Salary",
        type: "CREDIT",
        amount: 10000
      },
    ]
  };
}

export async function getMyAccount() {
  return getAccountDetails("1234567890");
}
