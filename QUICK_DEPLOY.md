# ⚡ Quick Deploy Guide

## 🚀 Deploy in 10 Minutes

### Step 1: Push to GitHub (2 minutes)

```bash
cd banking-transaction-simulator
git init
git add .
git commit -m "Initial commit"

# Create repo on GitHub, then:
git remote add origin https://github.com/YOUR_USERNAME/banking-transaction-simulator.git
git push -u origin main
```

### Step 2: Deploy Backend to Railway (3 minutes)

1. Go to https://railway.app/
2. Login with GitHub
3. Click "New Project" → "Deploy from GitHub repo"
4. Select your repository
5. Click "Add Service" → Select backend folder
6. Railway auto-detects Java and deploys
7. Click "Generate Domain" to get URL
8. Copy the URL (e.g., `https://your-app.railway.app`)

### Step 3: Update Frontend API URL (1 minute)

```bash
# Edit frontend/.env.production
VITE_API_URL=https://your-app.railway.app/api

git add .
git commit -m "Update API URL"
git push
```

### Step 4: Deploy Frontend to Vercel (3 minutes)

```bash
cd frontend
npm install -g vercel
vercel

# Follow prompts, then:
vercel --prod
```

Or use Vercel Dashboard:
1. Go to https://vercel.com/new
2. Import your GitHub repo
3. Set Root Directory: `frontend`
4. Add env var: `VITE_API_URL` = your Railway URL
5. Deploy

### Step 5: Update CORS (1 minute)

Add your Vercel URL to backend CORS:

```java
@CrossOrigin(origins = {
    "http://localhost:3000",
    "https://your-app.vercel.app"
})
```

Push changes:
```bash
git add .
git commit -m "Update CORS"
git push
```

## ✅ Done!

Your app is live at:
- Frontend: `https://your-app.vercel.app`
- Backend: `https://your-app.railway.app`

---

## 🎯 Even Faster: Use Railway for Both

Railway can host both frontend and backend:

1. Deploy backend (as above)
2. Add another service for frontend
3. Set Root Directory: `frontend`
4. Build Command: `npm run build`
5. Start Command: `npx serve -s dist -l $PORT`

No need for Vercel!

---

## 💡 Tips

- Railway gives $5 free credit/month
- Vercel is completely free for personal projects
- Both auto-deploy when you push to GitHub
- Use Railway for Java backend (better support)
- Use Vercel for React frontend (optimized)

**Need detailed instructions? See DEPLOYMENT_GUIDE.md**
