import Foundation
import UIKit
import SnapKit
import Shared

class LoginViewController: UIViewController {

    private let loginButton = UIButton()
    private let usernameTextField = UITextField()
    private let passwordTextField = UITextField()
    private let formStackView = UIStackView()
    private let loaderView = UIActivityIndicatorView()
    private let errorLabel = UILabel()

    override func viewDidLoad() {

        title = "GitHub Login"
        view.backgroundColor = .white

        formStackView.axis = .vertical
        formStackView.alignment = .fill
        formStackView.spacing = 20
        view.addSubview(formStackView)
        formStackView.snp.makeConstraints {
            $0.center.equalToSuperview()
            $0.width.equalToSuperview().inset(50)
        }

        errorLabel.textColor = .red
        errorLabel.textAlignment = .center
        errorLabel.isHidden = true
        errorLabel.lineBreakMode = .byWordWrapping
        errorLabel.numberOfLines = 0
        formStackView.addArrangedSubview(errorLabel)

        loaderView.isHidden = true
        loaderView.hidesWhenStopped = true
        loaderView.activityIndicatorViewStyle = .gray
        formStackView.addArrangedSubview(loaderView)

        usernameTextField.placeholder = "username"
        usernameTextField.autocorrectionType = .no
        usernameTextField.textAlignment = .center
        usernameTextField.autocapitalizationType = .none
        usernameTextField.layer.borderColor = UIColor.black.cgColor
        usernameTextField.layer.borderWidth = 0.5
        usernameTextField.layer.cornerRadius = 5
        formStackView.addArrangedSubview(usernameTextField)
        usernameTextField.snp.makeConstraints {
            $0.height.equalTo(50)
        }

        passwordTextField.placeholder = "password"
        usernameTextField.autocorrectionType = .no
        passwordTextField.isSecureTextEntry = true
        passwordTextField.textAlignment = .center
        passwordTextField.layer.borderColor = UIColor.black.cgColor
        passwordTextField.layer.borderWidth = 0.5
        passwordTextField.layer.cornerRadius = 5
        formStackView.addArrangedSubview(passwordTextField)
        passwordTextField.snp.makeConstraints {
            $0.height.equalTo(50)
        }

        loginButton.setTitle("Login", for: .normal)
        loginButton.setTitleColor(.black, for: .normal)
        loginButton.backgroundColor = .lightGray
        loginButton.layer.borderColor = UIColor.black.cgColor
        loginButton.layer.borderWidth = 0.5
        loginButton.layer.cornerRadius = 5
        formStackView.addArrangedSubview(loginButton)
        loginButton.snp.makeConstraints {
            $0.height.equalTo(50)
        }
        
        loginButton.addTarget(self, action: #selector(loginButtonDidTap), for: .touchUpInside)
    }
    
    @objc private func loginButtonDidTap() {
        errorLabel.isHidden = true
        loaderView.isHidden = false
        loaderView.startAnimating()
        
        guard let username = usernameTextField.text, !username.isEmpty else {
            self.errorLabel.isHidden = false
            self.errorLabel.text = "Please fill the username!"
            self.loaderView.stopAnimating()
            return
        }
        
        guard let password = passwordTextField.text, !password.isEmpty else {
            self.errorLabel.isHidden = false
            self.errorLabel.text = "Please fill the password!"
            self.loaderView.stopAnimating()
            return
        }
        
        GitHubApiClient(githubUserName: username, githubPassword: password).repos(
            successCallback:{ [weak self] repos in
                self?.loaderView.stopAnimating()

                let vc = RepoListViewController(repos: repos)
                self?.navigationController?.pushViewController(vc, animated: true)

                return StdlibUnit()
            }, errorCallback: { [weak self] error in
                self?.loaderView.stopAnimating()
                self?.errorLabel.isHidden = false

                self?.errorLabel.text = "Request failed! Error: \(error.message ?? "unknown")"
                
                return StdlibUnit()
        })
        
        view.endEditing(true)
    }
    
}
