rootProject.name = "tradeport"

include("security")
include("exception")
include("rabbitmq")

// User-Management
include("user-management")
include("user-management:user-api")
include("user-management:user-domain")
include("user-management:user-application")
include("user-management:user-infra")

// Customer-Management
include("customer-management")
include("customer-management:customer-domain")
include("customer-management:customer-infra")
include("customer-management:customer-application")
include("customer-management:customer-api")