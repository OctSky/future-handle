##git pull##
取回远程主机远程分支，与本地分支合并		git pull <remoteHost> <remoteBranch>:<localBranch>

如果本地分支为当前分支，可省去lcoalBranch，即 git pull <remoteHost> <remoteBranch>

如果当前分支与远程分支存在追踪关系，可省去remoteBranch  即  git pull <remoteHost> 

如果当前分支只存在一个追踪关系，则可直接用   git pull

清理远程已删除本地还存在的分支  git pull -p

git pull 相当于 git fetch + git merge


##创建分支##
基于当前分支创建本地分支 		git branch <localBranch>
基于当前分支创建并切换到新本地分支  git checkout -b <localBranch>
拉取远程分支分化一个本地新分支 git checkout -b <localBranch> <remoteBranch>

将本地分支推送到远程  git push --set-upstream origin <remoteBranch>

git push origin remoteBranchNew   ， 将本地分支推送并新建远程仓库分支
git branch --set-upstream-to=origin/remoteBranchNew  ， 建立本地分支与远程分支的关联


##全量合并master到当前分支##
git rebase master

合并本地多个commit  git rebase -i [startpoint] [endpoint]
将本地指定区间的commit复制到目标分支上  git rebase [startpoint] [endpoint] --onto [target branch]
摘取复制某个commit git cherry-pick [commitId]


对最近一次提交做修改		git commit -a -amend 

撤销最近一次commit并本地保留代码 git reset HEAD^
本地回退到最近一次commit  git reset --hard HEAD


删除分支   git branch -d localBranch
强制删除分支   git branch -D localBranch

查看各分支最后一次提交  git branch -v

查看哪些分支合并入当前分支   git branch -merged
查看哪些分支未合并入当前分支    git branch -no-merged

退出合并 git merge --abort 
清理我们为手动合并而创建但不再有用的额外文件 git clean 