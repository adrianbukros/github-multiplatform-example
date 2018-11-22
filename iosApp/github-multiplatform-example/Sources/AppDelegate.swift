import UIKit
import shared

@UIApplicationMain
class AppDelegate: UIResponder, UIApplicationDelegate {

    private let window: UIWindow = UIWindow()

    func application(
        _ application: UIApplication,
        didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]?) -> Bool {

        LoggerKt.doInitTimber()

        let navController = UINavigationController()
        navController.navigationBar.tintColor = .gray

        window.rootViewController = navController
        window.makeKeyAndVisible()

        navController.pushViewController(LoginViewController(), animated: false)

        return true
    }
}
