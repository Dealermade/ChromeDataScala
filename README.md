# ChromedScala
ChromedScala is a ChromeData API Client implemented in Scala as a play framework module.

## Usage

To use this module, add it as a dependency in your play application and inject
the client in your controller:

```scala
class MyController @Inject()(chromedClient: ChromedClient) {
  ...
}
```

The API credentials must be configured in `application.conf` under the section `chromed`:

```hocon
chromed {
  account {
    number = "..."
    secret = "..."
  }
}
```

Additionally, the default configuration defines two environment variables that might be used
to provide credentials to the client, `CHROMED_ACCOUNT_NUMBER` and `CHROMED_ACCOUNT_SECRET`.