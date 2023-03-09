function fn() {
	var config = {
		env: karate.env
	}
	if (!config.env){
		config.env = 'qa';
	}
	config.paramjson = karate.read('classpath:config/Parameterised.json')
	return config
}