## About Popular Movies
Movie adviser app made as part of Udacity android nanodegree, intended for learning purposes.

## Features
- shows:
  - popular movies
  - top rated movies
  - movie details
  - trailers

- keeps track of favorite movies

## Notable technical details
- content provider for storing already viewed content this way app works offline
- `glide` library for image caching
- [The Movie Database](https://www.themoviedb.org/) as data source

## Build
This is gradle project, you can build it using Android Studio (select `Import project`) or with [gradle CLI](http://developer.android.com/tools/building/building-cmdline.html).

[Android content provider generator](https://github.com/BoD/android-contentprovider-generator) have been used, look at `make-content-provider.bat` to see how to regenerate it in case of database scheme change.

## License
TODO
