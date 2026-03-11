# 🔄 Quick Restart Guide

## To See the Upgraded Dashboard

### Step 1: Restart Backend (IntelliJ IDEA)

1. Look at the bottom of IntelliJ where the console is running
2. Click the **red square button** (Stop) to stop the current backend
3. Click the **green play button** (▶) to restart
4. Wait for: **"Banking Transaction Simulator Started!"**

### Step 2: Refresh Frontend

The frontend should auto-reload. If not:

1. Go to your browser at http://localhost:3000
2. Press **Ctrl + Shift + R** (hard refresh)
3. Or stop frontend (Ctrl+C) and run: `npm run dev`

---

## 🎯 What You'll See

### New UI Elements

1. **Three Transaction Cards** (instead of two)
   - Deposit
   - Withdraw
   - Transfer (NEW!)

2. **Simulate Button**
   - "Simulate 20 Concurrent Transactions"
   - Click to generate random transactions

3. **Thread Activity Section** (NEW!)
   - Shows last 10 thread activities
   - Real-time updates
   - Thread names and operations

4. **Refined Design**
   - Thinner borders (1px instead of 2px)
   - Subtle shadows
   - Lighter, more professional look
   - Better spacing

---

## 🧪 Test the New Features

### 1. Test Transfer
```
1. In "Transfer" card:
   - From Account: Account 1
   - To Account: Account 2
   - Amount: 500
2. Click "Transfer"
3. Watch balance update
4. Check transaction table
```

### 2. Test Simulation
```
1. Click "Simulate 20 Concurrent Transactions"
2. Watch backend console for thread logs
3. See transaction table fill up
4. Check thread activity section
5. Observe balance changes
```

### 3. Watch Thread Activity
```
1. Perform any transaction
2. Look at "Recent Thread Activity" section
3. See thread name, type, and amount
4. Watch it update in real-time
```

---

## 🐛 Troubleshooting

### Backend won't restart?
```
1. Make sure you stopped the previous run
2. Check if port 8080 is free
3. Look for errors in IntelliJ console
```

### Frontend not updating?
```
1. Hard refresh: Ctrl + Shift + R
2. Clear browser cache
3. Restart frontend: Ctrl+C, then npm run dev
```

### Changes not visible?
```
1. Make sure backend restarted successfully
2. Check browser console (F12) for errors
3. Verify you're on http://localhost:3000
```

---

## 📊 Expected Console Output

### Backend Console (IntelliJ)
```
Banking Transaction Simulator Started!
pool-1-thread-1 deposited ₹750 | Balance: ₹5750
pool-1-thread-3 withdrew ₹200 | Balance: ₹5550
pool-1-thread-2 transferred ₹500 from Account 1 to Account 2 | Balance: ₹5050
```

### Frontend (Browser)
- Balance updates in real-time
- Thread activity shows recent operations
- Transaction table scrolls smoothly
- All three transaction forms work

---

## ✅ Verification Checklist

After restart, verify:

- [ ] Backend shows "Banking Transaction Simulator Started!"
- [ ] Frontend loads at http://localhost:3000
- [ ] Three transaction cards visible (Deposit, Withdraw, Transfer)
- [ ] "Simulate" button visible
- [ ] "Recent Thread Activity" section visible
- [ ] Transaction table has scrollbar
- [ ] Design looks lighter and more refined
- [ ] All borders are thinner (1px)

---

## 🎉 You're Ready!

Once both backend and frontend are running, you'll see the upgraded professional fintech dashboard!

**Enjoy testing the new features!** 🚀
