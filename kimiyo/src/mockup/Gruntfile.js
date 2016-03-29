module.exports = function (grunt) {

    grunt.initConfig({
        pkg: grunt.file.readJSON('package.json'),
        less: {
            dev: {
                options: {
                    paths: ["css"],
                    sourceMap: true,
                    cleancss: true,
                    sourceMapFilename: "css/main.css.map"
                },
                files: {
                    "css/main.css": "css/main.less"
                }
            }
        },
        uglify: {
            dev: {
                options: {
                    sourceMap: true
                },
                files: [
                    {
                        expand: true,
                        cwd: 'js',
                        src: 'main.js',
                        dest: 'js'
                    }
                ]
            }
        }
    });

    grunt.loadNpmTasks('grunt-contrib-less');
    grunt.loadNpmTasks('grunt-contrib-uglify');


    grunt.registerTask('default', ['less', 'uglify']);
};
