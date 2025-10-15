# SpaceX Rocket Lister (Main Branch)

The **SpaceX Rocket Lister** is an Android application built with **Kotlin** that fetches and displays data from the official **SpaceX REST API**.  
It demonstrates clean architecture, modular design, and API-driven UI rendering.

---

## Overview

This application retrieves rocket data such as name, first flight, height, and diameter using the **SpaceX v4 API** and displays it in a clean, scrollable list.  
Each rocket item can be selected to view detailed information on a separate screen.

---

## Tech Stack

- **Language:** Kotlin  
- **Architecture:** Fragment-based Navigation with Manual Dependency Injection (DI)  
- **Networking:** Retrofit + Gson  
- **Image Loading:** Glide  

---

## API Endpoint
All rocket data is fetched from the **SpaceX v4 API**:

```bash
# Base URL
https://api.spacexdata.com/v4/

# Endpoint
GET /rockets
```

**Also don't forget to check the other branches where different design patterns applied!**
