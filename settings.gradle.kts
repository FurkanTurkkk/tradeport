rootProject.name = "tradeport"

include(":common:security")
include(":common:exception")
include(":common:rabbitmq")
include(":common:cron-jobs")

// User-Management
include("user-management")
include("user-management:user-api")
include("user-management:user-application")
include("user-management:user-domain")
include("user-management:user-infra")

// Customer-Management
include("customer-management")
include("customer-management:customer-api")
include("customer-management:customer-application")
include("customer-management:customer-domain")
include("customer-management:customer-infra")

// Product-Management
include("product-management")
include("product-management:product-api")
include("product-management:product-application")
include("product-management:product-domain")
include("product-management:product-infra")

// Category-Management
include("category-management")
include("category-management:category-api")
include("category-management:category-application")
include("category-management:category-domain")
include("category-management:category-infra")

include("common")