# 🏗️ Deployment Architecture

## System Overview

```
┌─────────────────────────────────────────────────────────┐
│                        USERS                             │
│                    (Web Browsers)                        │
└────────────────────┬────────────────────────────────────┘
                     │
                     │ HTTPS
                     │
┌────────────────────▼────────────────────────────────────┐
│                    VERCEL CDN                            │
│              (Frontend Hosting)                          │
│                                                          │
│  ┌────────────────────────────────────────────────┐    │
│  │         React + Vite Application               │    │
│  │  - Dashboard UI                                │    │
│  │  - Transaction Forms                           │    │
│  │  - Real-time Updates                           │    │
│  └────────────────────────────────────────────────┘    │
│                                                          │
│  URL: https://banking-simulator.vercel.app              │
└────────────────────┬────────────────────────────────────┘
                     │
                     │ REST API Calls
                     │ (HTTPS)
                     │
┌────────────────────▼────────────────────────────────────┐
│                   RAILWAY                                │
│              (Backend Hosting)                           │
│                                                          │
│  ┌────────────────────────────────────────────────┐    │
│  │      Java Spring Boot Application              │    │
│  │  - Transaction Service                         │    │
│  │  - ExecutorService (5 threads)                 │    │
│  │  - REST API Endpoints                          │    │
│  │  - Thread-safe Operations                      │    │
│  └────────────────────────────────────────────────┘    │
│                                                          │
│  URL: https://banking-simulator.railway.app             │
└──────────────────────────────────────────────────────────┘
```

---

## Deployment Flow

```
┌──────────────┐
│  Developer   │
│   (You)      │
└──────┬───────┘
       │
       │ git push
       │
┌──────▼───────┐
│   GitHub     │
│  Repository  │
└──┬────────┬──┘
   │        │
   │        │ Auto-deploy
   │        │
   ▼        ▼
┌─────┐  ┌─────────┐
│Vercel│  │ Railway │
│(FE)  │  │  (BE)   │
└─────┘  └─────────┘
```

---

## Technology Stack

### Frontend (Vercel)
```
┌─────────────────────────────────┐
│         React 18.2.0            │
├─────────────────────────────────┤
│         Vite 5.0.8              │
├─────────────────────────────────┤
│         Axios 1.6.2             │
├─────────────────────────────────┤
│      Custom CSS (B&W)           │
└─────────────────────────────────┘
```

### Backend (Railway)
```
┌─────────────────────────────────┐
│    Spring Boot 3.2.0            │
├─────────────────────────────────┤
│         Java 21                 │
├─────────────────────────────────┤
│    ExecutorService (5)          │
├─────────────────────────────────┤
│      Maven 3.9.6                │
└─────────────────────────────────┘
```

---

## API Communication

```
Frontend (Vercel)
    │
    │ GET /api/balance
    ├──────────────────────────────────────►
    │                                        │
    │                                   Backend (Railway)
    │                                        │
    │ POST /api/deposit                      │
    ├──────────────────────────────────────►│
    │                                        │
    │ POST /api/withdraw                     │
    ├──────────────────────────────────────►│
    │                                        │
    │ POST /api/transfer                     │
    ├──────────────────────────────────────►│
    │                                        │
    │ POST /api/simulate                     │
    ├──────────────────────────────────────►│
    │                                        │
    │ GET /api/transactions                  │
    ├──────────────────────────────────────►│
    │                                        │
    │◄────────────────────────────────────────
    │         JSON Response
```

---

## Environment Variables

### Frontend (Vercel)
```
VITE_API_URL=https://your-backend.railway.app/api
```

### Backend (Railway)
```
PORT=8080
SPRING_PROFILES_ACTIVE=prod
```

---

## CORS Configuration

```java
@CrossOrigin(origins = {
    "http://localhost:3000",           // Local dev
    "https://*.vercel.app",            // Vercel preview
    "https://banking-simulator.vercel.app"  // Production
})
```

---

## Build Process

### Frontend Build (Vercel)
```
1. npm install
2. npm run build
3. Output: dist/
4. Deploy to CDN
5. Generate URL
```

### Backend Build (Railway)
```
1. mvn clean package
2. Create JAR file
3. java -jar app.jar
4. Start on $PORT
5. Generate URL
```

---

## Scaling Architecture

```
Current Setup (Free Tier):
┌─────────────────────────────────┐
│  Vercel: Unlimited bandwidth    │
│  Railway: $5 credit/month       │
│  Thread Pool: 5 threads         │
└─────────────────────────────────┘

Future Scaling:
┌─────────────────────────────────┐
│  Vercel: Pro plan ($20/mo)      │
│  Railway: Scale plan ($20/mo)   │
│  Thread Pool: 10-20 threads     │
│  Database: PostgreSQL           │
│  Cache: Redis                   │
└─────────────────────────────────┘
```

---

## Monitoring & Logs

### Vercel Dashboard
```
- Deployment logs
- Build status
- Performance metrics
- Error tracking
- Analytics
```

### Railway Dashboard
```
- Application logs
- CPU/Memory usage
- Deployment history
- Environment variables
- Custom domains
```

---

## Security Features

```
┌─────────────────────────────────┐
│  HTTPS Everywhere               │
├─────────────────────────────────┤
│  CORS Protection                │
├─────────────────────────────────┤
│  Environment Variables          │
├─────────────────────────────────┤
│  Thread-safe Operations         │
├─────────────────────────────────┤
│  Input Validation               │
└─────────────────────────────────┘
```

---

## Deployment Checklist

### Pre-Deployment
- [ ] Code pushed to GitHub
- [ ] Environment variables configured
- [ ] CORS settings updated
- [ ] Build commands verified

### Frontend (Vercel)
- [ ] Root directory: `frontend`
- [ ] Build command: `npm run build`
- [ ] Output directory: `dist`
- [ ] Environment variable: `VITE_API_URL`

### Backend (Railway)
- [ ] Root directory: `backend`
- [ ] Build command: `mvn clean package`
- [ ] Start command: `java -jar target/*.jar`
- [ ] Port: `$PORT`

### Post-Deployment
- [ ] Frontend loads correctly
- [ ] API calls work
- [ ] No CORS errors
- [ ] All features functional
- [ ] Performance acceptable

---

## Cost Breakdown

### Free Tier (Recommended for Demo)
```
Vercel:
- Bandwidth: 100GB/month
- Builds: Unlimited
- Cost: $0

Railway:
- Credit: $5/month
- Runtime: ~500 hours
- Cost: $0 (with free credit)

Total: FREE
```

### Production Tier
```
Vercel Pro:
- Bandwidth: 1TB/month
- Cost: $20/month

Railway Scale:
- Resources: 8GB RAM, 8 vCPU
- Cost: $20/month

Total: $40/month
```

---

## Backup & Recovery

### Code Backup
```
- GitHub repository (primary)
- Local development copy
- Git branches for versions
```

### Data Backup
```
Current: In-memory (resets on restart)
Future: PostgreSQL with daily backups
```

---

## Performance Metrics

### Expected Performance
```
Frontend (Vercel):
- Load time: < 2 seconds
- Time to Interactive: < 3 seconds
- Lighthouse score: 90+

Backend (Railway):
- API response: < 200ms
- Concurrent requests: 100+
- Thread pool: 5 threads
```

---

## Troubleshooting Guide

### Frontend Issues
```
Problem: White screen
Solution: Check browser console, verify API URL

Problem: API calls fail
Solution: Check CORS, verify backend is running

Problem: Build fails
Solution: Check package.json, verify dependencies
```

### Backend Issues
```
Problem: App won't start
Solution: Check logs, verify Java version

Problem: Out of memory
Solution: Increase Railway resources

Problem: Slow response
Solution: Increase thread pool size
```

---

## 🎯 Summary

Your banking simulator uses a modern, scalable architecture:

✅ Frontend on Vercel (CDN, fast, global)  
✅ Backend on Railway (Java support, auto-deploy)  
✅ HTTPS everywhere (secure)  
✅ Auto-deployment (push to deploy)  
✅ Free tier available (perfect for demo)  
✅ Easy to scale (upgrade when needed)  

**Ready for production deployment!** 🚀
