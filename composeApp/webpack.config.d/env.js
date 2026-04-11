config.plugins.push({
    apply: (compiler) => {
        compiler.options.plugins.push(
            new (require("webpack")).DefinePlugin({
                "process.env.BASE_URL": JSON.stringify(process.env.BASE_URL || "http://webvetcare:8080/api")
            })
        );
    }
});