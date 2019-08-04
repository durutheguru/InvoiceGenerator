var gulp = require('gulp');
var uglify = require('gulp-uglify');
var concat = require('gulp-concat');

var baseJSFiles = [
    // 3rd party
    'js/jquery.js',
    'js/bootstrap.min.js',
    'js/axios.min.js',
    'js/vue.min.js',

    'js/app/main.js',
    'js/app/util.js',
    'js/app/web.js'
];


//minified js
gulp.task('compress-timetable', function() {
    gulp
        .src(
        baseJSFiles.concat([
        'js/app/timetable/timetable.service.js',
        'js/app/timetable/timetable_upload_view_model.js'
        ]))
        .pipe(concat('app.min.js'))
        .pipe(uglify())
        .pipe(gulp.dest('./js/app/timetable/'))
});


