Vagrant.configure("2") do |config|
  config.vm.box = "debian/bullseye64"
  config.vm.provision :docker
  config.vm.network "forwarded_port", guest: 3000, host: 3000
  config.vm.network "forwarded_port", guest: 8080, host: 8080
  config.vm.network "forwarded_port", guest: 9090, host: 9090
  config.vm.network "forwarded_port", guest: 9100, host: 9100
end
