### HomeSweetHome : a real estate ad aggregator

This app is meant to aggregate real estate ads from multiple websites and be able to follow day-to-day updates on them.

The ad crawler has been coded in Kotlin/Js and make use of Puppeteer (Playwright support is the target). It stores the ads in a Mongo database.

For now, only LeBonCoin has been implemented for french ads. Note that they are using a bot detector and only puppeteer-firefox is not detected as is.

#### Run the crawler
Prerequisite: run a mongodb instance

Then `$ gradle run`

#### ToDo
- mock puppeteer in tests
- LBC location handling
- front + API
- configurable mongodb instance
- new websites handlers
