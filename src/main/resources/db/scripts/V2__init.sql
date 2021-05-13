

CREATE TABLE `client` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) NOT NULL
);

CREATE TABLE `pet` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) NOT NULL,
  `client_id` bigint(20) NOT NULL
);

--
-- Indexes for table `client`
--
ALTER TABLE `client`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `pet`
--
ALTER TABLE `pet`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for table `client`
--
ALTER TABLE `client`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `pet`
--
ALTER TABLE `pet`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;


--
-- Constraints for table `pet`
--
ALTER TABLE `pet`
  ADD CONSTRAINT `client_fk` FOREIGN KEY (`client_id`) REFERENCES `client` (`id`);
