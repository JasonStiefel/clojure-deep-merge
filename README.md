# Deep Merge

This acknowledge that merging could be simple or complex. Configuring merging should be easy and it should be robust. This library tackles some of the complexities of these statements. Please take a look at this [Clojure](https://clojure.org/) library and make any suggestions in pull requests or issues.

## Merge Requests
In order to make recommendations to this library, please,
1. Fork this repository
2. Create a branch (name it whatever you would like)
3. Make your changes to that branch
4. Create a pull request from that branch to the master branch of this repository

When you create the merge request, check the following values at the top where branches/repositories can be specified
* `base fork` should be `JasonStiefel/clojure-deep-merge`
* `base` should be `master`
* `head fork` should be your forked repository, created in step 1
* `compare` should be the branch you created in step 2

## Build Framework
This incorporates [Leiningen](https://leiningen.org/) for its build/test/deploy process.
### Testing
To test this project, run `lein test`