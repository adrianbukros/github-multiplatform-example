import Foundation
import UIKit
import SnapKit
import Shared

class RepoListViewController: UIViewController {

    private static let cellIdentifier = "repo-cell"

    private let tableView = UITableView()
    private var repos: [GitHubRepo]

    init(repos: [GitHubRepo]) {
        self.repos = repos
        super.init(nibName: nil, bundle: nil)
    }

    override func viewDidLoad() {
        title = "Your GitHub repos"
        view.backgroundColor = .white

        view.addSubview(tableView)
        tableView.snp.makeConstraints {
            $0.edges.equalToSuperview()
        }

        tableView.dataSource = self
        tableView.delegate = self
        tableView.register(UITableViewCell.self, forCellReuseIdentifier: RepoListViewController.cellIdentifier)
        tableView.reloadData()
    }

    required init?(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
}

extension RepoListViewController: UITableViewDataSource, UITableViewDelegate {
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return repos.count
    }

    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        guard let cell: UITableViewCell =
            self.tableView.dequeueReusableCell(withIdentifier: RepoListViewController.cellIdentifier)
            else { return UITableViewCell() }
        cell.selectionStyle = .none
        cell.textLabel?.text = self.repos[indexPath.row].name
        return cell
    }

    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        if let link = URL(string: repos[indexPath.row].htmlUrl) {
            UIApplication.shared.open(link)
        }
    }
}
