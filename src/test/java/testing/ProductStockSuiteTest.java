package testing;

import org.junit.platform.suite.api.*;
@Suite
@SelectPackages("testing")
@IncludeTags({"regression","sanity"})
class ProductStockSuiteTest {}
