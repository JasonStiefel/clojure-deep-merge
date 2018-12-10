# Deep Merge

This acknowledge that merging could be simple or complex. Configuring merging should be easy and it should be robust. This library tackles some of the complexities of these statements. Please take a look at this [Clojure](https://clojure.org/) library and make any suggestions in pull requests or issues.

You can use this library by adding it as a [dependency](https://github.com/technomancy/leiningen/blob/stable/doc/TUTORIAL.md#dependencies):

[![Clojars Project](https://img.shields.io/clojars/v/clojure-deep-merge.svg)](https://clojars.org/clojure-deep-merge)

## Usage
At this point, merging is divided into 3 types of comparisons:
* All maps - Handled with a recursive call using [`merge-with`](https://clojuredocs.org/clojure.core/merge-with)
* All non-map collections - Most of what has been considered so far
  * `concat-merge`/`concat-merge-with` - Puts all items into one vector
  * `distinct-merge`/`distinct-merge-with` - Puts all items into one deduplicated vector
  * `index-merge`/`index-merge-with` - Handled with recursive calls using all values at each index and puts the results in a vector
* Other - For all functions that end with `-with` take a first parameter of a function that will handle these situations and all functions that do not use the value from the last argument passed in

## Merge Requests
In order to make recommendations to this library, either please
1. Fork this repository
2. Create a branch (name it whatever you would like) in your repository created in step 1
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
To test this project, run `lein test`.