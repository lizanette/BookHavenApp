package com.example.bookhavenapp.domain.use_cases.app_entry

import com.example.bookhavenapp.domain.manager.LocalUserManager

class SaveAppEntry(private val localUserManager: LocalUserManager) {
    suspend operator fun invoke() {
        localUserManager.saveAppEntry()
    }
}
