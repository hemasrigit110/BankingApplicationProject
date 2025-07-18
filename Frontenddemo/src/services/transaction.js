// src/services/transaction.js (Mock version)

export async function transferFunds({ fromAccount, toAccount, amount, ifscCode }) {
  await new Promise(resolve => setTimeout(resolve, 500)); // simulate delay

  // Simulate success
  return {
    message: `₹${amount} transferred from ${fromAccount} to ${toAccount}`
  };
}
