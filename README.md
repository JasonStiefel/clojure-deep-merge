# Deep Merge

This acknowledge that merging could be simple or complex. Configuring merging should be easy and it should be robust. This library tackles some of the complexities of these statements. Please take a look at this [Clojure](https://clojure.org/) library and make any suggestions in pull requests or issues.

## Merge Requests
In order to make recommendations to this library, either please
1. Fork this repository
2. Create a branch (name it whatever you would like)
3. Make your changes to that branch
4. Run tests
5. [Create a pull request from that branch of the forked repository to the master branch of this repository](https://help.github.com/articles/creating-a-pull-request-from-a-fork/). Check the following values when creating it (steps 4 & 5):
   * `base fork` should be `JasonStiefel/clojure-deep-merge`
   * `base` should be `master`
   * `head fork` should be your forked repository, created in step 1
   * `compare` should be the branch you created in step 2
   
Or create an issue with specifics of what is wrong or what you would like to see. 

## Build Framework
This incorporates [Leiningen](https://leiningen.org/) for its build/test/deploy process.
### Testing
To test this project, run `lein test`