# 🚀 Deployment Guide

## Overview

This guide covers deploying your Banking Transaction Simulator to production:
- **Frontend** → Vercel (React/Vite)
- **Backend** → Railway (Java Spring Boot)

---

## 📋 Prerequisites

1. GitHub account
2. Vercel account (free): https://vercel.com/signup
3. Railway account (free): https://railway.app/
4. Git installed on your computer

---

## Part 1: Prepare Your Code

### Step 1: Initialize Git Repository

```bash
cd banking-transaction-simulator
git init
git add .
git commit -m "Initial commit: Banking Transaction Simulator"
```

### Step 2: Create GitHub Repository

1. Go to https://github.com/new
2. Repository name: `banking-transaction-simulator`
3. Make it Public or Private
4. Don't initialize with README (we already have one)
5. Click "Create repository"

### Step 3: Push to GitHub

```bash
git remote add origin https://github.com/YOUR_USERNAME/banking-transaction-simulator.git
git branch -M main
git push -u origin main
```

---

## Part 2: Deploy Backend to Railway

### Step 1: Sign Up for Railway

1. Go to https://railway.app/
2. Click "Login" → "Login with GitHub"
3. Authorize Railway

### Step 2: Create New Project

1. Click "New Project"
2. Select "Deploy from GitHub repo"
3. Select your `banking-transaction-simulator` repository
4. Railway will detect it's a monorepo

### Step 3: Configure Backend Service

1. Click "Add Service" → "GitHub Repo"
2. In settings, set:
   - **Root Directory**: `backend`
   - **Build Command**: `mvn clean package -DskipTests`
   - **Start Command**: `java -jar target/banking-transaction-simulator-1.0.0.jar`

### Step 4: Add Environment Variables

In Railway project settings, add:
```
PORT=8080
SPRING_PROFILES_ACTIVE=prod
```

### Step 5: Get Backend URL

1. Go to your Railway project
2. Click on the backend service
3. Go to "Settings" → "Networking"
4. Click "Generate Domain"
5. Copy the URL (e.g., `https://your-app.railway.app`)

---

## Part 3: Deploy Frontend to Vercel

### Step 1: Update API URL

1. Open `frontend/.env.production`
2. Replace with your Railway backend URL:
```
VITE_API_URL=https://your-app.railway.app/api
```

3. Commit and push:
```bash
git add .
git commit -m "Update production API URL"
git push
```

### Step 2: Deploy to Vercel

#### Option A: Using Vercel CLI (Recommended)

```bash
# Install Vercel CLI
npm install -g vercel

# Navigate to frontend
cd frontend

# Deploy
vercel

# Follow prompts:
# - Set up and deploy? Yes
# - Which scope? Your account
# - Link to existing project? No
# - Project name? banking-transaction-simulator
# - Directory? ./
# - Override settings? No

# Deploy to production
vercel --prod
```

#### Option B: Using Vercel Dashboard

1. Go to https://vercel.com/new
2. Click "Import Git Repository"
3. Select your `banking-transaction-simulator` repo
4. Configure:
   - **Framework Preset**: Vite
   - **Root Directory**: `frontend`
   - **Build Command**: `npm run build`
   - **Output Directory**: `dist`
5. Add Environment Variable:
   - Key: `VITE_API_URL`
   - Value: `https://your-app.railway.app/api`
6. Click "Deploy"

### Step 3: Get Frontend URL

After deployment, Vercel will provide a URL like:
```
https://banking-transaction-simulator.vercel.app
```

---

## Part 4: Configure CORS

### Update Backend CORS Settings

Edit `backend/src/main/java/com/bank/controller/TransactionController.java`:

```java
@CrossOrigin(origins = {
    "http://localhost:3000",
    "https://banking-transaction-simulator.vercel.app",
    "https://*.vercel.app"
})
```

Commit and push:
```bash
git add .
git commit -m "Update CORS for production"
git push
```

Railway will automatically redeploy.

---

## Part 5: Verify Deployment

### Test Backend

```bash
curl https://your-app.railway.app/api/balance
```

Expected response:
```json
{"balance":5000.0}
```

### Test Frontend

1. Open your Vercel URL
2. Check if balance loads
3. Try deposit/withdraw
4. Test simulation
5. Check browser console for errors (F12)

---

## 🐛 Troubleshooting

### Backend Issues

**Problem**: Backend won't start on Railway
```
Solution:
1. Check Railway logs
2. Verify Java version (should be 17+)
3. Check build command is correct
4. Ensure pom.xml is in backend folder
```

**Problem**: CORS errors
```
Solution:
1. Update @CrossOrigin in TransactionController
2. Add your Vercel domain
3. Redeploy backend
```

### Frontend Issues

**Problem**: API calls fail
```
Solution:
1. Check VITE_API_URL in Vercel environment variables
2. Verify backend is running
3. Check CORS configuration
4. Look at browser console (F12)
```

**Problem**: Build fails on Vercel
```
Solution:
1. Check Root Directory is set to "frontend"
2. Verify package.json exists
3. Check build command: npm run build
4. Check output directory: dist
```

---

## 📊 Deployment Checklist

### Backend (Railway)
- [ ] Repository pushed to GitHub
- [ ] Railway project created
- [ ] Root directory set to `backend`
- [ ] Build command configured
- [ ] Start command configured
- [ ] Domain generated
- [ ] Backend URL copied
- [ ] CORS updated with Vercel domain

### Frontend (Vercel)
- [ ] API URL updated in `.env.production`
- [ ] Changes committed and pushed
- [ ] Vercel project created
- [ ] Root directory set to `frontend`
- [ ] Environment variable `VITE_API_URL` added
- [ ] Deployment successful
- [ ] Frontend URL works

### Testing
- [ ] Backend health check passes
- [ ] Frontend loads correctly
- [ ] Balance displays
- [ ] Deposit works
- [ ] Withdraw works
- [ ] Transfer works
- [ ] Simulation works
- [ ] No CORS errors

---

## 🎯 Alternative Backend Hosting

If Railway doesn't work, try these alternatives:

### Option 1: Render.com

1. Go to https://render.com/
2. Create "New Web Service"
3. Connect GitHub repo
4. Settings:
   - Root Directory: `backend`
   - Build Command: `mvn clean package -DskipTests`
   - Start Command: `java -jar target/banking-transaction-simulator-1.0.0.jar`

### Option 2: Heroku

1. Install Heroku CLI
2. Create `Procfile` in backend folder:
```
web: java -jar target/banking-transaction-simulator-1.0.0.jar
```
3. Deploy:
```bash
cd backend
heroku create your-app-name
git subtree push --prefix backend heroku main
```

---

## 🔄 Continuous Deployment

Both Vercel and Railway support automatic deployments:

1. Push to GitHub main branch
2. Railway automatically rebuilds backend
3. Vercel automatically rebuilds frontend
4. Changes go live in minutes

---

## 💰 Cost Estimate

### Free Tier Limits

**Vercel (Frontend)**
- ✅ Unlimited deployments
- ✅ 100GB bandwidth/month
- ✅ Custom domain support
- ✅ Automatic HTTPS

**Railway (Backend)**
- ✅ $5 free credit/month
- ✅ ~500 hours runtime
- ✅ Automatic deployments
- ✅ Custom domain support

**Total**: FREE for development/demo purposes!

---

## 📝 Post-Deployment

### Share Your App

Frontend URL:
```
https://banking-transaction-simulator.vercel.app
```

Backend API:
```
https://your-app.railway.app/api
```

### Monitor Performance

**Railway Dashboard**:
- View logs
- Monitor CPU/Memory
- Check deployment status

**Vercel Dashboard**:
- View analytics
- Monitor performance
- Check build logs

---

## 🎉 You're Live!

Your Banking Transaction Simulator is now deployed and accessible worldwide!

**Next Steps**:
1. Share the URL with others
2. Monitor usage in dashboards
3. Update code and push to auto-deploy
4. Add custom domain (optional)

---

## 📞 Need Help?

- Railway Docs: https://docs.railway.app/
- Vercel Docs: https://vercel.com/docs
- GitHub Issues: Create issue in your repo

**Happy Deploying!** 🚀
