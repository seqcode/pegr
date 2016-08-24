
quartz {
    autoStartup = false
    jdbcStore = false
    waitForJobsToCompleteOnShutdown = true
    exposeSchedulerInRepository = false

    props {
        scheduler.skipUpdateCheck = true
    }
}

environments {
    development {
        quartz {
            autoStartup = false
        }
    }
    test {
        quartz {
            autoStartup = false
        }
    }
}
