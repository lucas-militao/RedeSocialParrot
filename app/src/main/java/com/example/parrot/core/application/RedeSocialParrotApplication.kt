package com.example.parrot.core.application

import android.app.Application
import com.example.parrot.BuildConfig
import com.example.parrot.BuildConfig.DEBUG
import com.example.parrot.core.SessionController
import com.example.parrot.modules.authentication.database.AuthenticationDatabase
import com.facebook.stetho.Stetho
import com.uphyca.stetho_realm.RealmInspectorModulesProvider
import io.realm.Realm
import io.realm.RealmConfiguration

class RedeSocialParrotApplication : Application(){

    override fun onCreate() {
        super.onCreate()

        setupRealm()
        setupStetho()

        SessionController.user = AuthenticationDatabase.getUser()
        SessionController.sessionAuthentication = AuthenticationDatabase.getSessionAuthentication()
    }


    private fun setupRealm() {
        Realm.init(this)

        val realmConfigBuilder = RealmConfiguration.Builder()
        realmConfigBuilder.schemaVersion(BuildConfig.VERSION_CODE.toLong())
        realmConfigBuilder.deleteRealmIfMigrationNeeded()

        Realm.setDefaultConfiguration(realmConfigBuilder.build())
    }

    private fun setupStetho() {
        if (DEBUG) {
            val realmInspector = RealmInspectorModulesProvider.builder(this)
                .withDeleteIfMigrationNeeded(true)
                .build()
            Stetho.initialize(Stetho.newInitializerBuilder(this)
                .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                .enableWebKitInspector(realmInspector)
                .build())
        }
    }
}