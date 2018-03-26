# Setup your git repository

1. get certificate from leho
2. add to git: `git config --global http.sslcainfo <certificate-path>`
3. clone this repository: `https://172.21.22.52/TI/Project-I/2018-Group-00`
4. change to your repository:
  * rename directory [optional]
  * git remote rename origin old-origin
  * git remote add origin git@172.21.22.52:TI/Project-I/2018-Group-<xx>.git
  * git push -u origin --all
  * git push -u origin --tags
4. start add files:
  * add files
  * commit and push
  * start feature branching
