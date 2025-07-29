# Understanding `domain/service` vs. `application/service`

> **Audience:**  
> This is a clear explanation of when to place a service class in `domain/service` versus `application/service`.  

---

## Table of Contents

1. [High-Level Overview](#high-level-overview)  
2. [Domain Services (`domain/service`)](#domain-services-domainservice)  
   - [Purpose and Responsibilities](#purpose-and-responsibilities)  
   - [Characteristics](#characteristics)  
   - [When to Create a Domain Service](#when-to-create-a-domain-service)  
   - [Example: Domain Service](#example-domain-service)  
3. [Application Services (`application/service`)](#application-services-applicationservice)  
   - [Purpose and Responsibilities](#purpose-and-responsibilities-1)  
   - [Characteristics](#characteristics-1)  
   - [When to Create an Application Service](#when-to-create-an-application-service)  
   - [Example: Application Service](#example-application-service)  
4. [Comparative Summary](#comparative-summary)  
5. [Quick “Decision Tree”](#quick-decision-tree)  
6. [Appendix: Folder Structure](#appendix-folder-structure)  

---

## High-Level Overview

In a **Hexagonal/Clean/Onion** architecture, code is organized into layers (or “circles”) to enforce clear separation of concerns. Two of these layers often contain “service” classes:

1. **Domain Services** (`domain/service`)  
   - Encapsulate pure business logic that operates on domain entities/value objects.  
   - Remain completely agnostic of how data is persisted or how input/output is delivered.

2. **Application Services** (`application/service`)  
   - Orchestrate “use cases” (application workflows) by coordinating domain services, repository ports, DTO mappings, and transaction boundaries.  
   - Know about repository interfaces (ports) and may map between DTOs and domain objects.  
   - Mediate between inbound adapters (e.g., web controllers) and outbound adapters (e.g., JPA repositories).

Below we dive deeply into each layer, explain their responsibilities, provide illustrative code examples, and offer guidelines on how to decide where a particular “service” belongs.

---

## Domain Services (`domain/service`)

### Purpose and Responsibilities

- **Encapsulate Domain-Specific Business Logic**  
  Domain services encapsulate business rules that cannot live exclusively inside a single entity or value object. They act on one or more domain entities, enforce invariants, and perform calculations that are “core” to your business.

- **Maintain Pure Domain Layer**  
  They do *not* interact with databases, external APIs, or any framework. Instead, they operate only on domain types (entities, value objects, enums, primitives).

- **Coordinate Multiple Domain Objects**  
  Whenever an operation naturally involves more than one entity—such as “transfer a player from Club A to Club B,” “calculate a player’s performance rating from match statistics,” or “apply a bulk discount to an order line”—that logic often belongs in a domain service.

### Characteristics

1. **Framework-Agnostic**  
   No JPA, no Spring annotations, no HTTP/JSON, no DTOs—pure Java/Kotlin/C# code.

2. **Stateless or Immutable**  
   Typically stateless (no instance fields) or immutable (all inputs come in, outputs go out). No side effects on external systems.

3. **Expresses Ubiquitous Language**  
   Methods and classes are named after business concepts: `PlayerEligibilityService`, `RatingCalculator`, `LoanProcessor`, etc.

4. **Reused Across Multiple Use Cases**  
   Domain services can be invoked by different application services or by other domain services. One “calculate performance” service might be reused in “end of match,” “player scouting,” and “fantasy points” calculations.

### When to Create a Domain Service

- **Multiple Entities Involved**  
  Logic requires two or more domain entities/VOs. Example: “determine whether a player can be transferred to a new club” may combine `Player`, `Contract`, and `Club` entities.

- **Complex Business Invariants**  
  If an entity would become bloated with methods, or if some logic doesn’t “belong” to any single entity, extract it into a service. For instance, `Player.calculateFairValue(...)` might not fit inside `Player` if it also requires league average data.

- **No External Dependencies**  
  The logic can be expressed entirely in terms of your domain model; there are no repository calls or API calls inside a domain service.

### Example: Domain Service

```java
// src/main/java/com/example/fantasy/domain/service/PlayerEligibilityService.java
package com.example.fantasy.domain.service;

import com.example.fantasy.domain.model.Player;
import com.example.fantasy.domain.model.PlayerStatus;

/**
 * A domain service that enforces the business rule:
 *   "If a player's current ability < 50 and they are on loan, then mark them as AVAILABLE_FOR_TRANSFER."
 *
 * This logic is purely about domain concepts and contains no infrastructure code.
 */
public class PlayerEligibilityService {

    /**
     * Evaluate and return an updated Player object with the correct status.
     * If eligibility criteria are met, status is changed to AVAILABLE_FOR_TRANSFER; otherwise unchanged.
     *
     * @param player the domain Player to evaluate
     * @return a new Player instance with updated status (immutable) or the original if no change
     */
    public Player evaluateTransferEligibility(Player player) {
        boolean shouldBeAvailable =
            player.isOnLoan()
            && player.getCurrentAbility() != null
            && player.getCurrentAbility() < 50;

        if (shouldBeAvailable && player.getStatus() != PlayerStatus.AVAILABLE_FOR_TRANSFER) {
            return player.withStatus(PlayerStatus.AVAILABLE_FOR_TRANSFER);
        }
        return player;
    }
}
